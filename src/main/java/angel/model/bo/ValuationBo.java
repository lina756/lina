package angel.model.bo;

import angel.model.vo.ValuationVo;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public class ValuationBo {
    private Integer id;
    //计价id
    private String valuationId;
    //计价类型  0：计件，1：计时
    private Integer valuationType;
    //工价
    private Double price;
    //加价
    private Double increasePrice;
    //款号id
    private String styleId;

    public ValuationBo() {}

    public ValuationBo(String valuationId,Integer valuationType, Double price,Double increasePrice) {
        this.valuationId = valuationId;
        this.valuationType = valuationType;
        this.price = price;
        this.increasePrice = increasePrice;
    }

    public ValuationVo createVo() {
        ValuationVo valuationVo = new ValuationVo();
        valuationVo.setId(getId());
        valuationVo.setValuationType(getValuationType());
        valuationVo.setValuationId(getValuationId());
        valuationVo.setPrice(getPrice());
        valuationVo.setIncreasePrice(getIncreasePrice());
        valuationVo.setStyleId(getStyleId());
        return valuationVo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    @Override
    public String toString() {
        return "ValuationBo{" +
                "id=" + id +
                ", valuationId='" + valuationId + '\'' +
                ", valuationType=" + valuationType +
                ", price=" + price +
                ", increasePrice=" + increasePrice +
                ", styleId='" + styleId + '\'' +
                '}';
    }
}
