package angel.model.vto;

/**
 * Created by 磷啊 on 2017/11/19.
 */
public class StyleVto {
    private String styleId;

    private String styleName;

    private String valuationId;

    private Integer valuationType;

    private Integer count;

    private Double price;

    private Double increasePrice;

    public StyleVto() {}

    public StyleVto(String styleId,String valuationId,Integer count) {
        this.styleId = styleId;
        this.valuationId = valuationId;
        this.count = count;
    }

    public StyleVto(String styleId,String valuationId,String styleName,Integer valuationType,Integer count,Double price,Double increasePrice) {
        this.styleId = styleId;
        this.valuationId = valuationId;
        this.styleName = styleName;
        this.valuationType = valuationType;
        this.count = count;
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
}
