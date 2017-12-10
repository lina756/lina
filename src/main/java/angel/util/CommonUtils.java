package angel.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.UUID;

/**
 * Created by 磷啊 on 2017/11/19.
 */
public class CommonUtils {

    public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static JsonElement toJson(Object obj) {
        return new Gson().toJsonTree(obj);
    }

    public static JsonElement toJsonWithFilter(Object obj,String ...filter) {
        return null;
    }
}
