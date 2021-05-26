package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowComponentNotFoundException extends FlowException {

    private static final long serialVersionUID = 2519788590435555395L;

    public FlowComponentNotFoundException() {
    }

    public FlowComponentNotFoundException(String message) {
        super(message);
    }

    public FlowComponentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowComponentNotFoundException(Throwable cause) {
        super(cause);
    }

    public FlowComponentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
