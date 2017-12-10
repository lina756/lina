package angel.service;

import angel.model.bo.ValuationBo;

/**
 * Created by 磷啊 on 2017/11/26.
 */
public interface ValuationService {
    String selectValuations(String styleId);
    String createValuation(ValuationBo valuationBo);
    String updateValuation(ValuationBo valuationBo);
    String deleteValuation(String valuationId);
}
