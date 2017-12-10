package angel.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by 磷啊 on 2017/11/12.
 */
public class ResponseUtils {
    public static String createResponse(ResponseStatus responseStatus,Object obj) {
        JsonObject result = new JsonObject();
        result.addProperty("code",responseStatus.code);
        result.addProperty("message",responseStatus.message);
        result.add("data",new Gson().toJsonTree(obj));
        return result.toString();
    }

    public static String successResponse(Object obj) {
        return createResponse(ResponseStatus.RESPONSE_Success,obj);
    }

    public static String createResponse(ResponseStatus responseStatus) {
        JsonObject result = new JsonObject();
        result.addProperty("code",responseStatus.code);
        result.addProperty("message",responseStatus.message);
        return result.toString();
    }

    public static String successResponse() {
        return createResponse(ResponseStatus.RESPONSE_Success);
    }
}
