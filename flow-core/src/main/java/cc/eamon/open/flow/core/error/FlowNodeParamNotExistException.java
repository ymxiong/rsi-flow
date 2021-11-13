package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNodeParamNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowNodeParamNotExistException(String flowNodeId, String scope,String paramName) {
        super("流程节点参数不存在" + ", 流程节点ID：" + flowNodeId + ", 域：" + scope + ", 参数名：" + paramName);
    }

    public FlowNodeParamNotExistException(String flowNodeId, String scope,String paramName, String message) {
        super(message);
    }

    public FlowNodeParamNotExistException(String paramName, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNodeParamNotExistException(String paramName, Throwable cause) {
        super(cause);
    }

    public FlowNodeParamNotExistException(String paramName, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
