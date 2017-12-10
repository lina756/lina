package angel.model.mapper.order;

import angel.model.bo.ValuationBo;
import angel.model.vo.ValuationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/26.
 */
public interface ValuationMapper {
    //计价方式
    List<ValuationBo> selectValuations(String styleId);
    int insertValuation(ValuationBo valuationBo);
    int insertValuations(@Param("list") List<ValuationVo> valuationVos);
    int updateValuation(ValuationBo valuationBo);
    int deleteValuation(String valuationId);
    int deleteValuationByStyleId(String styleId);
    ValuationBo selectValuationDetail(String valuationId);
}
