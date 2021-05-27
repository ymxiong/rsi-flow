package cc.eamon.open.flow.core.error;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 17:11:27
 */
public class FlowException extends RuntimeException {

    private static final long serialVersionUID = 6141109116612846635L;

    public FlowException() {
        super("流程调度错误");
    }

    public FlowException(String message) {
        super(message);
    }

    public FlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowException(Throwable cause) {
        super(cause);
    }

    public FlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
