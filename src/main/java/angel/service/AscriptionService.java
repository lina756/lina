package angel.service;

/**
 * Created by 磷啊 on 2017/11/25.
 */
public interface AscriptionService {
    /**
     * 创建归属
     *
     * @param ascriptions 归属名称
     * @return 字符串
     */
    String createAscription(String ascriptions);
    /**
     * 归属列表
     *
     * @return 字符串
     */
    String findAscriptions();

    /**
     * 删除归属
     *
     * @param id 归属id
     * @return 字符串
     */
    String deleteAscription(Integer id);
}
