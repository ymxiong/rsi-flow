package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNodeResultNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowNodeResultNotExistException(String flowNodeId, String scope, String name) {
        super("流程节点结果不存在" + ", 流程节点ID：" + flowNodeId + ", 域：" + scope + ", 结果名：" + name);
    }

    public FlowNodeResultNotExistException(String message) {
        super(message);
    }

    public FlowNodeResultNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNodeResultNotExistException(Throwable cause) {
        super(cause);
    }

    public FlowNodeResultNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
