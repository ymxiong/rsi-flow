package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNotFoundException extends FlowException{
    public FlowNotFoundException() {
    }

    public FlowNotFoundException(String message) {
        super(message);
    }

    public FlowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNotFoundException(Throwable cause) {
        super(cause);
    }

    public FlowNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
