package angel.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by 磷啊 on 2017/11/12.
 */
public class CheckUtils {

    public static boolean checkParam(Object ...params) {
       for (Object obj : params) {
           if (obj instanceof String) {
               if (StringUtils.isEmpty(obj)) {
                   return true;
               }
           }
           if (ObjectUtils.isEmpty(obj)) {
               return true;
           }
       }
       return false;
    }
}
