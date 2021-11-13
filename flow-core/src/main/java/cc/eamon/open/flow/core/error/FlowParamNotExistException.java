package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowParamNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowParamNotExistException(String flowId, String scope, String paramName) {
        super("流程参数不存在" + ", 流程ID：" + flowId + ", 域：" + scope + ", 参数名：" + paramName);
    }

    public FlowParamNotExistException(String flowId, String scope, String paramName, String message) {
        super(message);
    }

    public FlowParamNotExistException(String paramName, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowParamNotExistException(String paramName, Throwable cause) {
        super(cause);
    }

    public FlowParamNotExistException(String paramName, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
