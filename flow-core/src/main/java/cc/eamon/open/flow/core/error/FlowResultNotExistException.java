package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowResultNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowResultNotExistException(String flowNodeId, String scope, String name) {
        super("流程结果不存在" + ", 流程ID：" + flowNodeId + ", 域：" + scope + ", 结果名：" + name);
    }

    public FlowResultNotExistException(String flowNodeId, String scope, String name, String message) {
        super(message);
    }

    public FlowResultNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowResultNotExistException(Throwable cause) {
        super(cause);
    }

    public FlowResultNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
