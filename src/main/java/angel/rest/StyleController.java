package angel.rest;

import angel.model.bo.StyleBo;
import angel.model.vo.StyleVo;
import angel.model.vto.StyleRequestVto;
import angel.service.StyleService;
import angel.util.CheckUtils;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 磷啊 on 2017/11/25.
 */
@RestController
public class StyleController {
    private static Logger log = LoggerFactory.getLogger(StyleController.class);

    @Autowired
    private StyleService styleService;

    @RequestMapping(value = "/statistics/v1/styles",method = RequestMethod.GET)
    public String selectStyles() {
        return styleService.selectStyles();
    }

    @RequestMapping(value = "/statistics/v1/styleDetail",method = RequestMethod.GET)
    public String selectStyleDetail(@RequestParam("styleId") String styleId) {
        return styleService.selectStyleDetail(styleId);
    }

    @RequestMapping(value = "/statistics/v1/style",method = RequestMethod.POST)
    public String createStyle(@RequestBody StyleRequestVto requestVto) {
        if (CheckUtils.checkParam(requestVto.getStyleName(),
                requestVto.getStyleCode(),
                requestVto.getAscription(),
                requestVto.getPriceType(),
                requestVto.getPrice(),
                requestVto.getIncreasePrice())) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return styleService.createStyle(requestVto);
    }

    @RequestMapping(value = "/statistics/v1/style",method = RequestMethod.PUT)
    public String updateStyle(@RequestBody StyleBo styleBo) {
        return styleService.updateStyle(styleBo);
    }

    @RequestMapping(value = "/statistics/v1/style",method = RequestMethod.DELETE)
    public String deleteStyle(@RequestParam("styleId") String styleId) {
        return styleService.deleteStyle(styleId);
    }
}
