package angel.config.exception;

/**
 * Created by 磷啊 on 2017/11/5.
 */
public class CheckObjectNullException extends NullPointerException {
    public CheckObjectNullException() {
        super("验证方法报错，所要验证的参数不能为空！！！");
    }
}
