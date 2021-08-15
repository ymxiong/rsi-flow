package cc.eamon.open.flow.model.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowModelNotDefinedException extends RuntimeException {

    private static final long serialVersionUID = 2519788590435555395L;

    public FlowModelNotDefinedException() {
    }

    public FlowModelNotDefinedException(String message) {
        super(message);
    }

    public FlowModelNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowModelNotDefinedException(Throwable cause) {
        super(cause);
    }

    public FlowModelNotDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
