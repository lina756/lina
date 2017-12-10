package angel.model.vo;

/**
 * Created by 磷啊 on 2017/11/6.
 */
public class ValuationVo {
    private Integer Id;
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

    public ValuationVo() {}

    public ValuationVo(Integer id,String valuationId,Integer valuationType, Double price) {
        this.Id = id;
        this.valuationId = valuationId;
        this.valuationType = valuationType;
        this.price = price;
    }

    public ValuationVo(Integer valuationType, Double price) {
        this.valuationType = valuationType;
        this.price = price;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
}
