package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowCallItselfException extends FlowException {

    private static final long serialVersionUID = -2371046550815962280L;

    public FlowCallItselfException() {
    }

    public FlowCallItselfException(String message) {
        super(message);
    }

    public FlowCallItselfException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowCallItselfException(Throwable cause) {
        super(cause);
    }

    public FlowCallItselfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
