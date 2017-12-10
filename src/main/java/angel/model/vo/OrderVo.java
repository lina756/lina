package angel.model.vo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单类
 *
 * Created by 磷啊 on 2017/11/5.
 */
public class OrderVo {
    private Integer id;
    //订单id
    private String orderId;
    //单号
    private String orderCode;
    //创单人
    private String createOrderPerson;

    private List<OrderStyleVo> orderStyleVos;

    public OrderVo() {}

    public OrderVo(Integer id,
                   String orderId,
                   String createOrderPerson,
                   List<StyleVo> styleVos) {
        this.id = id;
        this.orderId = orderId;
        this.createOrderPerson = createOrderPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateOrderPerson() {
        return createOrderPerson;
    }

    public void setCreateOrderPerson(String createOrderPerson) {
        this.createOrderPerson = createOrderPerson;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<OrderStyleVo> getOrderStyleVos() {
        return orderStyleVos;
    }

    public void setOrderStyleVos(List<OrderStyleVo> orderStyleVos) {
        this.orderStyleVos = orderStyleVos;
    }
}
