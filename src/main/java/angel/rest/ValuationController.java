package angel.rest;

import angel.model.bo.ValuationBo;
import angel.service.ValuationService;
import angel.util.CheckUtils;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import com.alibaba.druid.util.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by 磷啊 on 2017/11/26.
 */
@RestController
public class ValuationController {
    private static Logger log = LoggerFactory.getLogger(ValuationController.class);

    @Autowired
    private ValuationService valuationService;

    @RequestMapping(value = "/statistics/v1/valuation",method = RequestMethod.GET)
    public String selectValuations(String styleId) {
        if (StringUtils.isEmpty(styleId)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return valuationService.selectValuations(styleId);
    }

    @RequestMapping(value = "/statistics/v1/valuation",method = RequestMethod.POST)
    public String createValuation(@RequestBody ValuationBo valuationBo) {
        if (ObjectUtils.isEmpty(valuationBo)
                || CheckUtils.checkParam(
                        valuationBo.getPrice(),
                valuationBo.getStyleId(),
                valuationBo.getValuationType(),
                valuationBo.getIncreasePrice()
        )) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return valuationService.createValuation(valuationBo);
    }

    @RequestMapping(value = "/statistics/v1/valuation",method = RequestMethod.PUT)
    public String updateValuation(@RequestBody ValuationBo valuationBo) {
        if (ObjectUtils.isEmpty(valuationBo)
                || null == valuationBo.getValuationId()) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return valuationService.updateValuation(valuationBo);
    }

    @RequestMapping(value = "/statistics/v1/valuation",method = RequestMethod.DELETE)
    public String deleteValuation(@RequestParam("valuationId")String valuationId) {
        if (StringUtils.isEmpty(valuationId)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return valuationService.deleteValuation(valuationId);
    }
}
