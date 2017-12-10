package angel.rest;

import angel.model.vo.AscriptionVo;
import angel.service.AscriptionService;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 磷啊 on 2017/11/1.
 */
@RestController
public class AscriptionController {
    private static final Logger log = LoggerFactory.getLogger(AscriptionController.class);

    @Autowired
    private AscriptionService ascriptionService;

    @RequestMapping(value = "/statistics/v1/ascription",method = RequestMethod.GET)
    public String findAscriptions() {
        return ascriptionService.findAscriptions();
    }

    @RequestMapping(value = "/statistics/v1/ascription",method = RequestMethod.POST)
    public String createAscription(@RequestBody String ascriptions) {
        if (StringUtils.isEmpty(ascriptions)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return ascriptionService.createAscription(ascriptions);
    }

    @RequestMapping(value = "/statistics/v1/ascription",method = RequestMethod.DELETE)
    public String deleteAscription(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResponseUtils.createResponse(ResponseStatus.PESPONSE_ParamNull);
        }
        return ascriptionService.deleteAscription(id);
    }
}
