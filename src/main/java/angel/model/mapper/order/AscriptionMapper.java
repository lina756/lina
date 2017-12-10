package angel.model.mapper.order;

import angel.model.bo.AscriptionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public interface AscriptionMapper {
    //归属
    int insertAscription(@Param("list") List<String> ascriptions);
    List<AscriptionBo> selectAscriptions();
    int deleteAscription(Integer id);
}
