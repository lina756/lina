package angel.model.vo;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/6.
 */
public class StyleVo {
    private Integer Id;
    //款式id
    private String styleId;
    //款号
    private String styleCode;
    //款式名称
    private String styleName;
    //数量
    private Integer count;
    //人数
    private Integer personCount;
    //计价方式
    private List<ValuationVo> valuationVos;

    public StyleVo() {}

    public StyleVo(Integer id,String styleId,String styleName,Integer count,Integer numberOfPeople,List<ValuationVo> valuationVos) {
        this.Id = id;
        this.styleId = styleId;
        this.styleName = styleName;
        this.count = count;
        this.personCount = numberOfPeople;
        this.valuationVos = valuationVos;
    }

    public StyleVo(String styleName,Integer count,Integer numberOfPeople,List<ValuationVo> valuationVos) {
        this.styleName = styleName;
        this.count = count;
        this.personCount = numberOfPeople;
        this.valuationVos = valuationVos;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public List<ValuationVo> getValuationVos() {
        return valuationVos;
    }

    public void setValuationVos(List<ValuationVo> valuationVos) {
        this.valuationVos = valuationVos;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }
}
