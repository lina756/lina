package angel.rest;

import angel.model.vo.OrderStyleVo;
import angel.model.vto.BatchDeleteOrderRequestVto;
import angel.model.vto.CheckVto;
import angel.model.vto.OrderStyleVto;
import angel.model.vto.OrderVto;
import angel.service.OrderService;
import angel.util.CheckUtils;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 磷啊 on 2017/11/5.
 */
@RestController
public class OrderController {
    private static Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/statistics/v1/order", method = RequestMethod.POST)
    public String createOrder(@RequestBody OrderVto orderVto) {
        if (CheckUtils.checkParam(
                orderVto.getCreateOrderPerson(),
                orderVto.getOrderCode(),
                orderVto.getWorkDate(),
                orderVto.getStyleId(),
                orderVto.getAscription(),
                orderVto.getCount(),
                orderVto.getValuationId())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.createOrder(orderVto);
    }

    @RequestMapping(value = "/statistics/v1/orders", method = RequestMethod.GET)
    public String selectOrders(@RequestParam(value = "filter",required = false) String filter,
        @RequestParam(value = "checkStatus",required = false,defaultValue = "-1") Integer checkStatus) {
        return orderService.selectOrders(filter,checkStatus);
    }

    @RequestMapping(value = "/statistics/v1/orders/batchDelete", method = RequestMethod.POST)
    public String batchOrders(@RequestBody Map<String,Object> map) {
        if (null == map
                || StringUtils.isEmpty(MapUtils.getString(map,"orderIds"))) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.batchOrders(MapUtils.getString(map,"orderIds"));
    }

    @RequestMapping(value = "/statistics/v1/orderDetail", method = RequestMethod.GET)
    public String selectOrderDetail(@RequestParam("orderId") String orderId) {
        if (null == orderId || "".equals(orderId.trim())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.selectOrderDetail(orderId);
    }

    @RequestMapping(value = "/statistics/v1/order",method = RequestMethod.PUT)
    public String updateOrder(@RequestBody OrderVto orderVto) {
        if (CheckUtils.checkParam(
                orderVto.getCreateOrderPerson(),
                orderVto.getOrderCode(),
                orderVto.getOrderId())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.updateOrder(orderVto);
    }

    @RequestMapping(value = "/statistics/v1/order", method = RequestMethod.DELETE)
    public String deleteOrder(String orderId, Integer id) {
        if (null == orderId || "".equals(orderId.trim())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.deleteOrder(orderId, id);
    }

    @RequestMapping(value = "/statistics/v1/orderStyle", method = RequestMethod.DELETE)
    public String deleteOrderStyle(String orderId, Integer id) {
        if (null == orderId || "".equals(orderId.trim())
                || null == id || "".equals(id)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.deleteOrderStyle(orderId, id);
    }

    @RequestMapping(value = "/statistics/v1/orderStyle", method = RequestMethod.POST)
    public String createOrderStyle(@RequestBody OrderVto orderVto) {
        if (CheckUtils.checkParam(
                orderVto.getOrderId(),
                orderVto.getWorkDate(),
                orderVto.getStyleId(),
                orderVto.getAscription(),
                orderVto.getCount(),
                orderVto.getValuationId())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.createOrderStyle(orderVto);
    }

    @RequestMapping(value = "/statistics/v1/orderStyle", method = RequestMethod.GET)
    public String orderStyleDetail(@RequestParam Integer orderStyleId) {
        if (null == orderStyleId) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.selectOrderStyleDetail(orderStyleId);
    }

    @RequestMapping(value = "/statistics/v1/orderStyle", method = RequestMethod.PUT)
    public String updateOrderStyle(@RequestBody OrderStyleVto orderVto) {
        if (CheckUtils.checkParam(
                orderVto.getOrderStyleId(),
                orderVto.getOrderId(),
                orderVto.getWorkDate(),
                orderVto.getStyleCode(),
                orderVto.getStyleName(),
                orderVto.getAscription(),
                orderVto.getCount(),
                orderVto.getValuationType(),
                orderVto.getPrice()
                )) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.updateOrderStyle(orderVto);
    }

    @RequestMapping(value = "/statistics/v1/check", method = RequestMethod.PUT)
    public String checkOrderStyle(@RequestBody CheckVto checkVto) {
        if (CheckUtils.checkParam(
                checkVto.getIds(),
                checkVto.getCheckPersonName())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.checkOrderStyle(checkVto);
    }

    @RequestMapping(value = "/statistics/v1/statisticsResult", method = RequestMethod.GET)
    public String statisticsResult(String id,@RequestParam("type") Integer statisticsType) {
        if (null == id
                || "".equals(id.trim())
                ||null == statisticsType
                ||statisticsType >= 2
                ||statisticsType <0 ) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.statisticsResult(id,statisticsType);
    }

    @RequestMapping(value = "/statistics/v1/order/batchDelete",method = RequestMethod.POST)
    public String batchDeleteOrderStyle(@RequestBody BatchDeleteOrderRequestVto batchDeleteOrderRequestVto) {
        if (null == batchDeleteOrderRequestVto
                || null == batchDeleteOrderRequestVto.getOrderId()
                || "".equals(batchDeleteOrderRequestVto.getOrderStyleIds().replaceAll(" ",""))
                || null == batchDeleteOrderRequestVto.getOrderStyleIds()
                || "".equals(batchDeleteOrderRequestVto.getOrderStyleIds().replaceAll(" ",""))) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return orderService.batchDeleteOrderStyle(batchDeleteOrderRequestVto.getOrderId(),batchDeleteOrderRequestVto.getOrderStyleIds());
    }

    @RequestMapping(value = "/statistics/v1/exportExcel",method = RequestMethod.POST)
    public void reportExcel(HttpServletResponse response, HttpServletRequest request) {
        String orderIds = request.getParameter("exportOrderIds");
        if (null == orderIds
                || "".equals(orderIds.replaceAll(" ", ""))) {
            log.error(ResponseStatus.PESPONSE_ParamNull.message);
        }
        String result = null;
        try {
             result = orderService.exportExcel(response,orderIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result != null) {
            log.info(ResponseStatus.RESPONSE_Success.message);
        }
    }

}
