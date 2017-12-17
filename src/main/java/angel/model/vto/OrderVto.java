package angel.model.vto;

import angel.model.bo.OrderBo;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/19.
 */
public class OrderVto {
    private Integer id;
    //订单id
    private String orderId;
    //单号
    private String orderCode;
    //创单人
    private String createOrderPerson;
    //工作日期
    private String workDate;
    //款号id
    private String styleId;
    //计价方式id
    private String valuationId;
    //创建时间
    private Long created;
    //摘要
    private String ascription;
    //人数
    private Integer persons;
    //数量
    private Integer count;
    //备注
    private String remark;
    public OrderVto() {}

    public OrderVto(String createOrderPerson,List<StyleVto> styles) {
        this.createOrderPerson = createOrderPerson;
    }

    public OrderBo createBo() {
        OrderBo orderBo = new OrderBo();
        orderBo.setOrderId(getOrderId());
        orderBo.setOrderCode(getOrderCode());
        orderBo.setCreateOrderPerson(getCreateOrderPerson());
        orderBo.setCreated(getCreated());
        return orderBo;
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

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getValuationId() {
        return valuationId;
    }

    public void setValuationId(String valuationId) {
        this.valuationId = valuationId;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }
}
