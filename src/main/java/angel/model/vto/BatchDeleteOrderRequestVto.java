package angel.model.vto;

import java.util.List;

/**
 * Author hongql
 *
 * @Date Created by blm on 13/12/17.
 * @Description 描述
 */
public class BatchDeleteOrderRequestVto {
    private String orderId;
    private String orderStyleIds;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStyleIds() {
        return orderStyleIds;
    }

    public void setOrderStyleIds(String orderStyleIds) {
        this.orderStyleIds = orderStyleIds;
    }
}
