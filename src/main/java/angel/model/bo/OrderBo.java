package angel.model.bo;

import angel.model.vo.OrderStyleVo;
import angel.model.vo.OrderVo;
import angel.model.vto.CompleteOrderVto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 磷啊 on 2017/12/3.
 */
public class OrderBo {
    private Integer id;
    //订单id
    private String orderId;
    //单号
    private String orderCode;
    //创单人
    private String createOrderPerson;
    //创建时间
    private Long created;

    private List<OrderStyleBo> orderStyleBoList;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCreateOrderPerson() {
        return createOrderPerson;
    }

    public void setCreateOrderPerson(String createOrderPerson) {
        this.createOrderPerson = createOrderPerson;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public List<OrderStyleBo> getOrderStyleBoList() {
        return orderStyleBoList;
    }

    public void setOrderStyleBoList(List<OrderStyleBo> orderStyleBoList) {
        this.orderStyleBoList = orderStyleBoList;
    }

    public OrderVo createVo() {
        OrderVo orderVo = new OrderVo();
        orderVo.setId(getId());
        orderVo.setOrderId(getOrderId());
        orderVo.setOrderCode(getOrderCode());
        orderVo.setCreateOrderPerson(getCreateOrderPerson());
        if (null != getOrderStyleBoList() && getOrderStyleBoList().size() > 0) {
            List<OrderStyleVo> orderStyleVos = new ArrayList<OrderStyleVo>(getOrderStyleBoList().size());
            for (OrderStyleBo orderStyleBo : getOrderStyleBoList()) {
                OrderStyleVo orderStyleVo = new OrderStyleVo();
                orderStyleVo.setId(orderStyleBo.getId());
                orderStyleVo.setWorkDate(orderStyleBo.getWorkDate());
                orderStyleVo.setStyleCode(orderStyleBo.getStyleCode());
                orderStyleVo.setStyleName(orderStyleBo.getStyleName());
                orderStyleVo.setAscription(orderStyleBo.getAscription());
                orderStyleVo.setPersons(orderStyleBo.getPersons());
                orderStyleVo.setCount(orderStyleBo.getCount());
                orderStyleVo.setValuationType(orderStyleBo.getValuationType());
                orderStyleVo.setPrice(orderStyleBo.getPrice());
                orderStyleVo.setIncreasePrice(orderStyleBo.getIncreasePrice());
                orderStyleVo.setTotalPrice(orderStyleBo.getTotalPrice());
                orderStyleVo.setCheck(orderStyleBo.getCheck());
                orderStyleVo.setCheckPerson(orderStyleBo.getCheckPerson());
                orderStyleVo.setRemark(orderStyleBo.getRemark());
                orderStyleVos.add(orderStyleVo);
            }
            orderVo.setOrderStyleVos(orderStyleVos);
        }
        return orderVo;
    }

    public List<OrderStyleVo> createOrderStyleVos() {
        List<OrderStyleVo> orderVos = new ArrayList<OrderStyleVo>();
        if (CollectionUtils.isEmpty(orderStyleBoList)) {
            return orderVos;
        }
        for (OrderStyleBo orderStyleBo : orderStyleBoList) {
            OrderStyleVo orderVo = new OrderStyleVo();
            orderVo.setId(orderStyleBo.getId());
            orderVo.setOrderId(getOrderId());
            orderVo.setOrderCode(getOrderCode());
            orderVo.setCreateOrderPerson(getCreateOrderPerson());
            orderVo.setWorkDate(orderStyleBo.getWorkDate());
            orderVo.setStyleCode(orderStyleBo.getStyleCode());
            orderVo.setStyleName(orderStyleBo.getStyleName());
            orderVo.setAscription(orderStyleBo.getAscription());
            orderVo.setPersons(orderStyleBo.getPersons());
            orderVo.setCount(orderStyleBo.getCount());
            orderVo.setValuationType(orderStyleBo.getValuationType());
            orderVo.setPrice(orderStyleBo.getPrice());
            orderVo.setIncreasePrice(orderStyleBo.getIncreasePrice());
            orderVo.setTotalPrice(orderStyleBo.getTotalPrice());
            orderVo.setCheck(orderStyleBo.getCheck());
            orderVo.setCheckPerson(orderStyleBo.getCheckPerson());
            orderVo.setRemark(orderStyleBo.getRemark());
            orderVos.add(orderVo);
        }
        return orderVos;
    }

    public List<CompleteOrderVto> createCompleteOrder() {
        List<CompleteOrderVto> completeOrderVtos = new ArrayList<CompleteOrderVto>();
        if (null == getOrderStyleBoList()) {
            return completeOrderVtos;
        }
        for (OrderStyleBo orderStyleBo : getOrderStyleBoList()) {
            CompleteOrderVto completeOrderVto = new CompleteOrderVto();
            completeOrderVto.setOrderCode(getOrderCode());
            completeOrderVto.setCreateOrderPerson(getCreateOrderPerson());
            completeOrderVto.setWorkDate(orderStyleBo.getWorkDate());
            completeOrderVto.setStyleCode(orderStyleBo.getStyleCode());
            completeOrderVto.setStyleName(orderStyleBo.getStyleName());
            completeOrderVto.setAscription(orderStyleBo.getAscription());
            completeOrderVto.setPersons(orderStyleBo.getPersons());
            completeOrderVto.setCount(orderStyleBo.getCount());
            completeOrderVto.setValuationType(orderStyleBo.getValuationType() == 0? "计时":"计件");
            completeOrderVto.setPrice(orderStyleBo.getPrice());
            completeOrderVto.setIncreasePrice(orderStyleBo.getIncreasePrice());
            completeOrderVto.setTotalPrice(orderStyleBo.getTotalPrice());
            completeOrderVto.setCheck(orderStyleBo.getCheck() == 0? "待验收":"已验收");
            completeOrderVto.setCheckPerson(orderStyleBo.getCheckPerson());
            completeOrderVto.setRemark(orderStyleBo.getRemark());
            completeOrderVtos.add(completeOrderVto);
        }
        return completeOrderVtos;
    }

    @Override
    public String toString() {
        return "OrderBo{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", createOrderPerson='" + createOrderPerson + '\'' +
                '}';
    }
}
