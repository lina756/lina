package angel.service.impl;

import angel.model.bo.ValuationBo;
import angel.model.mapper.order.ValuationMapper;
import angel.service.ValuationService;
import angel.util.CommonUtils;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/26.
 */
@Service
public class ValuationServiceImpl implements ValuationService {
    private static Logger log = LoggerFactory.getLogger(ValuationServiceImpl.class);

    @Autowired
    private ValuationMapper valuationMapper;

    @Override
    public String selectValuations(String styleId) {
        List<ValuationBo> valuationBos = valuationMapper.selectValuations(styleId);
        return ResponseUtils.successResponse(valuationBos);
    }

    @Override
    public String createValuation(ValuationBo valuationBo) {
        valuationBo.setValuationId(CommonUtils.createUUID());
        int result = valuationMapper.insertValuation(valuationBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        return ResponseUtils.successResponse(valuationBo);
    }

    @Override
    public String updateValuation(ValuationBo valuationBo) {
        int result = valuationMapper.updateValuation(valuationBo);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_DealError);
        }
        return ResponseUtils.successResponse(valuationBo.getValuationId());
    }

    @Override
    public String deleteValuation(String valuationId) {
        int result = valuationMapper.deleteValuation(valuationId);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_DealError);
        }
        return ResponseUtils.successResponse(valuationId);
    }
}
