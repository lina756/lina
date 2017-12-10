package angel.service;

import angel.model.bo.StyleBo;
import angel.model.vo.StyleVo;
import angel.model.vto.StyleRequestVto;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public interface StyleService {
    /**
     * 创建款式
     *
     * @param requestVto 款式参数
     * @return 字符串
     */
    String createStyle(StyleRequestVto requestVto);

    String selectStyleDetail(String styleId);

    /**
     * 更新款式
     *
     * @param styleBo 款式参数
     * @return 字符串
     */
    String updateStyle(StyleBo styleBo);

    /**
     * 所有款式列表
     *
     * @return 字符串
     */
    String selectStyles();

    /**
     * 删除款式
     *
     * @param styleId 款式id
     * @return 字符串
     */
    String deleteStyle(String styleId);
}
