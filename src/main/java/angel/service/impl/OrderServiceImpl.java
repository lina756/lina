package angel.service.impl;

import angel.model.bo.OrderBo;
import angel.model.bo.OrderStyleBo;
import angel.model.bo.StyleBo;
import angel.model.bo.ValuationBo;
import angel.model.mapper.order.OrderMapper;
import angel.model.mapper.order.StyleMapper;
import angel.model.mapper.order.ValuationMapper;
import angel.model.vo.OrderStatisticsVo;
import angel.model.vo.OrderStyleVo;
import angel.model.vo.OrderVo;
import angel.model.vto.CheckVto;
import angel.model.vto.CompleteOrderVto;
import angel.model.vto.OrderVto;
import angel.service.OrderService;
import angel.util.ExcelUtil;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static angel.util.CommonUtils.createUUID;

/**
 *
 * @author 磷啊
 * @date 2017/11/12
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StyleMapper styleMapper;

    @Autowired
    private ValuationMapper valuationMapper;

    @Transactional
    @Override
    public String createOrder(OrderVto orderVto) {
        StyleBo styleBo = styleMapper.selectStyleDetail(orderVto.getStyleId());
        if (ObjectUtils.isEmpty(styleBo)) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_NULL_STYLE);
        }
        ValuationBo valuationBo = valuationMapper.selectValuationDetail(orderVto.getValuationId());
        if (ObjectUtils.isEmpty(valuationBo)) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_NULL_VALUATION);
        }
        String orderId = createUUID();
        Long now = System.currentTimeMillis();
        orderVto.setCreated(now);
        orderVto.setOrderId(orderId);
        OrderBo orderBo = orderVto.createBo();
        int result = orderMapper.insertOrder(orderBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        orderVto.setOrderId(orderId);
        OrderStyleBo orderStyleBo = new OrderStyleBo(orderVto, styleBo, valuationBo);
        result = orderMapper.insertOrderStyle(orderStyleBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        return ResponseUtils.successResponse();
    }

    @Override
    public String updateOrder(OrderVto orderVto) {
        OrderBo orderBo = orderVto.createBo();
        int result = orderMapper.updateOrder(orderBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        return ResponseUtils.successResponse();
    }

    @Override
    public String selectOrders(String filter,Integer checkStatus) {
        List<OrderBo> orderBos = orderMapper.selectOrders(filter,checkStatus);
        List<OrderStyleVo> orderVos = new ArrayList<OrderStyleVo>();
        for (OrderBo orderBo : orderBos) {
            orderVos.addAll(orderBo.createOrderStyleVos());
        }
        return ResponseUtils.successResponse(orderVos);
    }

    @Override
    public String selectOrderDetail(String orderId) {
        OrderBo orderBo = orderMapper.selectOrderDetail(orderId);
        if (null == orderBo) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_UNLL_ORDER);
        }
        OrderVo orderVo = orderBo.createVo();
        return ResponseUtils.successResponse(orderVo);
    }

    @Transactional
    @Override
    public String deleteOrder(String orderId, Integer id) {
        int result = orderMapper.deleteOrderStyle(orderId, id);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_UNLL_ORDER);
        }
        int count = orderMapper.statisticsOrderStyles(orderId);
        if (count == 0) {
            orderMapper.deleteOrder(orderId);
        }
        return ResponseUtils.successResponse(orderId);
    }

    @Override
    public String deleteOrderStyle(String orderId, Integer id) {
        int result = orderMapper.deleteOrderStyle(orderId,id);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_UNLL_ORDER);
        }
        return ResponseUtils.successResponse(id);

    }

    @Override
    public String createOrderStyle(OrderVto orderVto) {
        StyleBo styleBo = styleMapper.selectStyleDetail(orderVto.getStyleId());
        if (ObjectUtils.isEmpty(styleBo)) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_NULL_STYLE);
        }
        ValuationBo valuationBo = valuationMapper.selectValuationDetail(orderVto.getValuationId());
        if (ObjectUtils.isEmpty(valuationBo)) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_NULL_VALUATION);
        }
        OrderStyleBo orderStyleBo = new OrderStyleBo(orderVto, styleBo, valuationBo);
        int result = orderMapper.insertOrderStyle(orderStyleBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        return ResponseUtils.successResponse(orderStyleBo);
    }

    @Override
    public String checkOrderStyle(CheckVto checkVto) {
        String Ids = checkVto.getIds();
        List<String> idList = Arrays.asList(Ids.split(","));
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        List<Integer> orderStyleIds = new ArrayList<Integer>(idList.size());
        for (String id : idList) {
            Integer orderStyleId = StringUtils.stringToInteger(id);
            orderStyleIds.add(orderStyleId);
        }
        orderMapper.checkOrderStyle(orderStyleIds,checkVto.getCheckPersonName());
        return ResponseUtils.successResponse();
    }

    @Override
    public String statisticsResult(String id, Integer statisticsType) {
        List<OrderStyleBo> orderStyleBos;
        if (statisticsType .equals(0)) {
            orderStyleBos = orderMapper.selectOrderStylesByOrderCode(id);
        }else{
            orderStyleBos = orderMapper.selectOrderStyles(id);
        }
        if (null == orderStyleBos || orderStyleBos.size() == 0) {
            return ResponseUtils.createResponse(ResponseStatus.UNDEFIND_UNLL_ORDERSTYLE);
        }
        int count = orderStyleBos.size();
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal checkPrice = new BigDecimal(0);
        BigDecimal unCheckPrice = new BigDecimal(0);
        for (OrderStyleBo orderStyleBo : orderStyleBos) {
            if (orderStyleBo.getCheck() == 0) {
                unCheckPrice = unCheckPrice.add(new BigDecimal(orderStyleBo.getTotalPrice()));
            }else {
                checkPrice = checkPrice.add(new BigDecimal(orderStyleBo.getTotalPrice()));
            }
        }
        totalPrice = checkPrice.add(unCheckPrice);
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo(count,
                totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(),
                checkPrice.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(),
                unCheckPrice.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()

        );
        return ResponseUtils.successResponse(orderStatisticsVo);
    }

    @Override
    public String exportExcel(HttpServletResponse response, String orderId) throws IOException {
        List<String> orderIds = Arrays.asList(orderId.split(","));
        if (orderIds.size() == 0){
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        List<OrderBo> orderBos = orderMapper.selectOrdersInOrderIds(orderIds);
        if (null == orderBos
                || orderBos.size() == 0) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=statistics.xls");
        List<CompleteOrderVto> completeOrderVtos = new ArrayList<CompleteOrderVto>(50);
        for (OrderBo orderBo : orderBos) {
            List<CompleteOrderVto> completeOrderVto = orderBo.createCompleteOrder();
            completeOrderVtos.addAll(completeOrderVto);
        }
        ExcelUtil.exportOrderExcel(completeOrderVtos,response.getOutputStream());
        return ResponseUtils.successResponse();
    }

}
