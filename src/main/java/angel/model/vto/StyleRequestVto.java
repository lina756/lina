package angel.model.vto;

import angel.model.bo.StyleBo;
import angel.model.bo.ValuationBo;

/**
 * Created by 磷啊 on 2017/12/1.
 */
public class StyleRequestVto {
    private Integer id;
    //款式id
    private String styleId;
    //款号
    private String styleCode;
    //款式名称
    private String styleName;

    private String ascription;

    private Integer priceType;

    private Double price;

    private Double increasePrice;

    public StyleBo createStyle() {
        StyleBo styleBo = new StyleBo();
        styleBo.setStyleName(getStyleName());
        styleBo.setStyleCode(getStyleCode());
        return styleBo;
    }

    public ValuationBo createValuation() {
        ValuationBo valuationBo = new ValuationBo();
        valuationBo.setValuationType(getPriceType());
        valuationBo.setPrice(getPrice());
        valuationBo.setIncreasePrice(getIncreasePrice());
        return valuationBo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
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

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
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

    @Override
    public String toString() {
        return "StyleRequestVto{" +
                "id=" + id +
                ", styleId='" + styleId + '\'' +
                ", styleCode='" + styleCode + '\'' +
                ", styleName='" + styleName + '\'' +
                ", ascription='" + ascription + '\'' +
                ", priceType=" + priceType +
                ", price=" + price +
                ", increasePrice=" + increasePrice +
                '}';
    }
}
