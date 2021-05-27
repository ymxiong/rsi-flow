package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ParamNotMatchException extends RuntimeException {

    private static final long serialVersionUID = 6749099088861453660L;

    public ParamNotMatchException(String flowId, String functionParam) {
        super("流程参数不匹配，请更改配置信息：" + flowId + " 流程代码参数名：" + functionParam);
    }

    public ParamNotMatchException(String flowId, String functionParam, String message) {
        super(message);
    }

    public ParamNotMatchException(String flowId, String functionParam, String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNotMatchException(String flowId, String functionParam, Throwable cause) {
        super(cause);
    }

    public ParamNotMatchException(String flowId, String functionParam, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
