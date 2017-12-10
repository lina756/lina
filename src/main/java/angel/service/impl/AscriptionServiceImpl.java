package angel.service.impl;

import angel.model.bo.AscriptionBo;
import angel.model.mapper.order.AscriptionMapper;
import angel.model.mapper.order.OrderMapper;
import angel.service.AscriptionService;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static angel.util.CommonUtils.toJson;

/**
 * Created by 磷啊 on 2017/11/25.
 */
@Service
public class AscriptionServiceImpl implements AscriptionService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private AscriptionMapper ascriptionMapper;

    @Override
    public String createAscription(String ascriptions) {
        ascriptionMapper.insertAscription(Arrays.asList(ascriptions.split(",")));
        return ResponseUtils.successResponse();
    }

    @Override
    public String findAscriptions() {
        List<AscriptionBo> ascriptionBos = ascriptionMapper.selectAscriptions();
        JsonObject result = new JsonObject();
        result.add("ascriptions",toJson(ascriptionBos));
        return ResponseUtils.successResponse(result);
    }

    @Override
    public String deleteAscription(Integer id) {
        int result = ascriptionMapper.deleteAscription(id);
        if (result <=0 ) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_DealError);
        }
        return ResponseUtils.successResponse();
    }
}
