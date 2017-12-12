package angel.service.impl;

import angel.model.bo.StyleBo;
import angel.model.bo.ValuationBo;
import angel.model.mapper.order.AscriptionMapper;
import angel.model.mapper.order.StyleMapper;
import angel.model.mapper.order.ValuationMapper;
import angel.model.vo.StyleVo;
import angel.model.vto.StyleRequestVto;
import angel.service.StyleService;
import angel.util.ResponseStatus;
import angel.util.ResponseUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static angel.util.CommonUtils.createUUID;

/**
 * Created by 磷啊 on 2017/11/25.
 */
@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleMapper styleMapper;

    @Autowired
    private ValuationMapper valuationMapper;

    @Autowired
    private AscriptionMapper ascriptionMapper;

    @Transactional
    @Override
    public String createStyle(StyleRequestVto requestVto) {
        String styleId = createUUID();
        StyleBo styleBo = requestVto.createStyle();
        styleBo.setStyleId(styleId);
        int result = styleMapper.insertStyle(styleBo);
        if (result <= 0 ){
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        ValuationBo valuationBo = requestVto.createValuation();
        valuationBo.setValuationId(createUUID());
        valuationBo.setStyleId(styleId);
        result = valuationMapper.insertValuation(valuationBo);
        if (result <= 0 ){
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_CreateError);
        }
        return ResponseUtils.successResponse();
    }

    @Override
    public String selectStyleDetail(String styleId) {
        StyleBo styleBo = styleMapper.selectStyleDetail(styleId);
        StyleVo styleVo = styleBo.createVo();
        return ResponseUtils.successResponse(styleVo);
    }

    @Override
    public String updateStyle(StyleBo styleBo) {
        int result = styleMapper.updateStyle(styleBo);
        if (result > 0) {
            return ResponseUtils.successResponse();
        }
        return ResponseUtils.createResponse(ResponseStatus.RESPONSE_DealError);
    }

    @Override
    public String selectStyles() {
        List<StyleBo> styleBos = styleMapper.selectStyles();
        List<StyleVo> styleVos = new ArrayList<StyleVo>();
        if (CollectionUtils.isEmpty(styleBos)) {
            return ResponseUtils.successResponse();
        }
        for (StyleBo styleBo : styleBos) {
            StyleVo styleVo = styleBo.createVo();
            styleVos.add(styleVo);
        }
        return ResponseUtils.successResponse(styleVos);
    }

    @Transactional
    @Override
    public String deleteStyle(String styleId) {
        valuationMapper.deleteValuationByStyleId(styleId);
        int result = styleMapper.deleteStyle(styleId);
        if (result <= 0) {
            return ResponseUtils.createResponse(ResponseStatus.RESPONSE_DealError);
        }
        return ResponseUtils.successResponse();
    }
}
