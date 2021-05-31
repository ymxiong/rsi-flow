package cc.eamon.open.flow.core.error;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 17:11:27
 */
public class FlowRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 6141109116612846635L;

    public FlowRuntimeException(String flowId) {
        super("流程调度错误: " + flowId);
    }

    public FlowRuntimeException(String flowId, String message) {
        super("流程调度错误: " + flowId + " " + message);
    }

    public FlowRuntimeException(String flowId, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowRuntimeException(String flowId, Throwable cause) {
        super(cause);
    }

    public FlowRuntimeException(String flowId, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
