package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2128684229676299592L;

    public FlowNotFoundException(String flowId) {
        super("流程未找到: " + flowId);
    }

    public FlowNotFoundException(String flowId, String message) {
        super(message);
    }

    public FlowNotFoundException(String flowId, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNotFoundException(String flowId, Throwable cause) {
        super(cause);
    }

    public FlowNotFoundException(String flowId, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
