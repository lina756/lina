package angel.util;

/**
 * Created by 磷啊 on 2017/11/12.
 */
public enum ResponseStatus {
    RESPONSE_Success(200,"处理成功"),
    RESPONSE_DealError(104,"处理失败"),
    RESPONSE_CreateError(102,"新增数据失败"),
    PESPONSE_ParamNull(101,"参数有误"),

    UNDEFIND_NULL_VALUATION(4001,"未知的计价方式"),
    UNDEFIND_NULL_STYLE(4002,"未知的款式"),
    UNDEFIND_UNLL_ORDER(4003,"未知的订单"),
    UNDEFIND_UNLL_ORDERSTYLE(4004,"未知的单号/款号");

    public Integer code;
    public String message;

    ResponseStatus(Integer code,String message) {
     this.code = code;
     this.message = message;
    }
}
