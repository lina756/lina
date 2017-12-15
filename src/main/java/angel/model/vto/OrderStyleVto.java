package angel.model.vto;

import angel.model.bo.OrderStyleBo;

import java.math.BigDecimal;

/**
 * Created by 磷啊 on 2017/11/19.
 */
public class OrderStyleVto {
    private String orderId;

    private Integer orderStyleId;

    private String workDate;

    private String styleId;

    private String styleCode;

    private String styleName;

    private String valuationId;

    private Integer valuationType;

    private Integer persons;

    private Integer count;

    private Double price;

    private Double increasePrice;

    private String remark;

    private String ascription;

    public OrderStyleVto() {}

    public OrderStyleVto(String orderId,StyleVto styleVto) {
        this.orderId = orderId;
        this.styleId = styleVto.getStyleId();
        this.styleName = styleVto.getStyleName();
        this.valuationId = styleVto.getValuationId();
        this.valuationType = styleVto.getValuationType();
        this.count = styleVto.getCount();
        this.price = styleVto.getPrice();
        this.increasePrice = styleVto.getIncreasePrice();
    }

    public OrderStyleBo createBo() {
        OrderStyleBo orderStyleBo = new OrderStyleBo();
        orderStyleBo.setOrderId(getOrderId());
        orderStyleBo.setId(getOrderStyleId());
        orderStyleBo.setWorkDate(getWorkDate());
        orderStyleBo.setStyleCode(getStyleCode());
        orderStyleBo.setStyleName(getStyleName());
        orderStyleBo.setAscription(getAscription());
        orderStyleBo.setPersons(getPersons());
        orderStyleBo.setCount(getCount());
        orderStyleBo.setValuationType(getValuationType());
        orderStyleBo.setPrice(getPrice());
        orderStyleBo.setIncreasePrice(getIncreasePrice());
        orderStyleBo.setRemark(getRemark());
        BigDecimal totalPrice = new BigDecimal(getPrice());
        if (null != getIncreasePrice()) {
            totalPrice = totalPrice.add(new BigDecimal(getIncreasePrice()));
        }
        orderStyleBo.setTotalPrice(totalPrice.multiply(new BigDecimal(getCount())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        return orderStyleBo;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getValuationId() {
        return valuationId;
    }

    public void setValuationId(String valuationId) {
        this.valuationId = valuationId;
    }

    public Integer getValuationType() {
        return valuationType;
    }

    public void setValuationType(Integer valuationType) {
        this.valuationType = valuationType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIncreasePrice() {
        return increasePrice;
    }

    public void setIncreasePrice(Double increasePrice) {
        this.increasePrice = increasePrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
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

    public Integer getOrderStyleId() {
        return orderStyleId;
    }

    public void setOrderStyleId(Integer orderStyleId) {
        this.orderStyleId = orderStyleId;
    }
}
