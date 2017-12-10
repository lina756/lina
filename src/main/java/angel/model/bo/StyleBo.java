package angel.model.bo;

import angel.model.vo.StyleVo;
import angel.model.vo.ValuationVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public class StyleBo {
    private Integer id;

    private String styleId;

    //款号
    private String styleCode;
    //款式名称
    private String styleName;

    private List<ValuationBo> valuationBos;

    public StyleBo() {}

    public StyleBo(String styleId,String styleName,List<ValuationBo> valuationBos) {
        this.styleId = styleId;
        this.styleName = styleName;
        this.valuationBos = valuationBos;
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

    public List<ValuationBo> getValuationBos() {
        return valuationBos;
    }

    public void setValuationBos(List<ValuationBo> valuationBos) {
        this.valuationBos = valuationBos;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StyleVo createVo() {
        StyleVo styleVo = new StyleVo();
        styleVo.setId(getId());
        styleVo.setStyleId(getStyleId());
        styleVo.setStyleName(getStyleName());
        styleVo.setStyleCode(getStyleCode());
        if (!CollectionUtils.isEmpty(getValuationBos())) {
            List<ValuationVo> valuationVos = new ArrayList<ValuationVo>(getValuationBos().size());
            for (ValuationBo valuationBo : getValuationBos()) {
                ValuationVo valuationVo = valuationBo.createVo();
                valuationVos.add(valuationVo);
            }
            styleVo.setValuationVos(valuationVos);
        }
        return styleVo;
    }

    @Override
    public String toString() {
        return "StyleBo{" +
                "styleId='" + styleId + '\'' +
                ", styleCode='" + styleCode + '\'' +
                ", styleName='" + styleName + '\'' +
                ", valuationBos=" + valuationBos +
                '}';
    }
}
