package angel.model.vto;

/**
 * Created by 磷啊 on 2017/11/19.
 */
public class OrderStyleVto {
    private String orderId;

    private String workDate;

    private String styleId;

    private String styleName;

    private String valuationId;

    private Integer valuationType;

    private Integer count;

    private Double price;

    private Double increasePrice;

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
}
