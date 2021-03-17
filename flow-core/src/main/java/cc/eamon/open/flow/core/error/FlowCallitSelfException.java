package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowCallitSelfException extends FlowException{
    public FlowCallitSelfException() {
    }

    public FlowCallitSelfException(String message) {
        super(message);
    }

    public FlowCallitSelfException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowCallitSelfException(Throwable cause) {
        super(cause);
    }

    public FlowCallitSelfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
