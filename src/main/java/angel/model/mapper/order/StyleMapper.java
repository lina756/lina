package angel.model.mapper.order;

import angel.model.bo.StyleBo;
import angel.model.vo.StyleVo;
import angel.model.vto.StyleVto;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public interface StyleMapper {
    //款式
    int insertStyle(StyleBo styleBo);
    int updateStyle(StyleBo styleBo);
    StyleBo selectStyleDetail(String styleId);
    StyleVto selectStyleOne(String styleId, String valuationId);
    List<StyleBo> selectStyles();
    int deleteStyle(String styleId);
}
