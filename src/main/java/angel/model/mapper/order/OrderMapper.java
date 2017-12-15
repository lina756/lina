package angel.model.mapper.order;

import angel.model.bo.OrderBo;
import angel.model.bo.OrderStyleBo;
import angel.model.vto.BatchDeleteOrderRequestVto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/12.
 */
public interface OrderMapper {
    //订单
    List<OrderBo> selectOrders(@Param("filter") String filter, @Param("checkStatus") Integer checkStatus);

    OrderBo selectOrderDetail(String orderId);

    int insertOrder(OrderBo orderBo);

    int updateOrder(OrderBo orderBo);

    int updateOrderStyle(OrderStyleBo orderStyleBo);

    int insertOrderStyle(OrderStyleBo orderStyleBo);

    OrderStyleBo selectOrderStyleDetail(Integer orderStyleId);

    int statisticsOrderStyles(String orderId);

    int deleteOrderStyle(String orderId, Integer id);

    void deleteOrder(String orderId);

    void checkOrderStyle(@Param("orderStyleIds") List<Integer> orderStyleIds,
                         @Param("checkPerson") String checkPerson);

    void batchDeleteOrderStyleByOrderList(@Param("list") List<BatchDeleteOrderRequestVto> batchDeleteOrderRequestVtos);

    void batchDeleteOrderStyle(@Param("orderId") String orderId, @Param("list") List<Integer> orderStyleIds);

    List<OrderStyleBo> selectOrderStylesByOrderCode(@Param("id") String id);

    List<OrderStyleBo> selectOrderStyles(@Param("id") String id);

    List<OrderBo> selectOrdersInOrderIds(@Param("list") List<BatchDeleteOrderRequestVto> orderIds);

}
