package angel.model.vo;

/**
 * Created by 磷啊 on 2017/12/5.
 */
public class OrderStyleVo {
    private Integer id;
    //订单id
    private String orderId;
    //单号
    private String orderCode;
    //创单人
    private String createOrderPerson;
    //工作日期
    private String workDate;

    private String styleCode;

    private String styleName;

    private Integer valuationType;

    private Double price;

    private Double increasePrice;

    private Double totalPrice;
    //创建时间
    private Long created;
    //摘要
    private String ascription;
    //人数
    private Integer persons;
    //数量
    private Integer count;

    private Integer check;

    private String checkPerson;
    //备注
    private String remark;

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

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
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

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
