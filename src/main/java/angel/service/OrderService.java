package angel.service;

import angel.model.bo.StyleBo;
import angel.model.vo.StyleVo;
import angel.model.vto.CheckVto;
import angel.model.vto.OrderVto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author 磷啊
 * @date 2017/11/12
 */
public interface OrderService {
    /**
     * 创建订单接口
     *
     * @param orderVto 订单参数
     * @return 返回字符串
     */
    String createOrder(OrderVto orderVto);

    /**
     * 更新订单操作
     *
     * @param orderVto
     * @return
     */
    String updateOrder(OrderVto orderVto);

    /**
     * 查询订单列表
     *
     * @return
     */
    String selectOrders(String filter,Integer checkStatus);

    /**
     * 订单详情
     *
     * @return
     */
    String selectOrderDetail(String orderId);

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    String deleteOrder(String orderId,Integer id);

    /**
     * 删除订单中的款式
     *
     * @param id
     * @return
     */
    String deleteOrderStyle(String orderId,Integer id);

    /**
     * 创建订单中的款式
     *
     * @param orderVto
     * @return
     */
    String createOrderStyle(OrderVto orderVto);

    /**
     * 验收
     *
     * @param checkVto
     * @return
     */
    String checkOrderStyle(CheckVto checkVto);

    /**
     * 统计
     *
     * @param id
     * @param statisticsType
     * @return
     */
    String statisticsResult(String id,Integer statisticsType);

    String exportExcel(HttpServletResponse response,String orderIds) throws IOException;
}
