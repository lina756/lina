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
    private Integer orderStyleId;

    public BatchDeleteOrderRequestVto() {}

    public BatchDeleteOrderRequestVto(String orderId,Integer orderStyleId) {
        this.orderId = orderId;
        this.orderStyleId = orderStyleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStyleId() {
        return orderStyleId;
    }

    public void setOrderStyleId(Integer orderStyleId) {
        this.orderStyleId = orderStyleId;
    }

    public String getOrderStyleIds() {
        return orderStyleIds;
    }

    public void setOrderStyleIds(String orderStyleIds) {
        this.orderStyleIds = orderStyleIds;
    }
}
