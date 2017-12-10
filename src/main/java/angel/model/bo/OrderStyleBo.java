package angel.model.bo;

import angel.model.vto.OrderVto;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * Created by 磷啊 on 2017/12/3.
 */
public class OrderStyleBo {
    private Integer id;

    private String orderId;

    private String workDate;

    private String styleCode;

    private String styleName;

    private Integer valuationType;

    private String ascription;

    private Integer persons;

    private Integer count;

    private Double price;

    private Double increasePrice;

    private Double totalPrice;

    private String checkPerson;

    private int check;

    private String remark;

    public OrderStyleBo() {}

    public OrderStyleBo(OrderVto orderVto, StyleBo styleBo, ValuationBo valuationBo) {
        this.orderId = orderVto.getOrderId();
        this.workDate = orderVto.getWorkDate();
        this.styleCode = styleBo.getStyleCode();
        this.styleName = styleBo.getStyleName();
        this.valuationType = valuationBo.getValuationType();
        this.ascription = orderVto.getAscription();
        this.persons = orderVto.getPersons();
        this.count = orderVto.getCount();
        this.price = valuationBo.getPrice();
        this.increasePrice = valuationBo.getIncreasePrice();
        BigDecimal realPrice = new BigDecimal(valuationBo.getPrice());
        BigDecimal totalPrice = new BigDecimal(0);
        if (!ObjectUtils.isEmpty(valuationBo.getIncreasePrice())) {
            realPrice = realPrice.add(new BigDecimal(valuationBo.getIncreasePrice()));
        }
        totalPrice = totalPrice.add(realPrice).multiply(new BigDecimal(orderVto.getCount()));
        this.totalPrice = totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.remark = orderVto.getRemark();
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

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Integer getValuationType() {
        return valuationType;
    }

    public void setValuationType(Integer valuationType) {
        this.valuationType = valuationType;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
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
