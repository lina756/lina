package angel.util;

import angel.config.exception.CheckObjectNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by 磷啊 on 2017/11/5.
 */
public class CheckUtil {
    private static final Logger log = LoggerFactory.getLogger(CheckUtil.class);

    public static boolean checkParams(Object object, String... params) throws NoSuchFieldException, IllegalAccessException {
        if (ObjectUtils.isEmpty(object)) {
            throw new CheckObjectNullException();
        }
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (String param : params) {
            for (Field field : fields) {
                System.out.println(field.getName());
                if (!field.getName().equals(param))
                    continue;
                field.setAccessible(true);
                Object result = field.get(object);
                System.out.println(result.getClass());
            }
        }
        return true;
    }
}
