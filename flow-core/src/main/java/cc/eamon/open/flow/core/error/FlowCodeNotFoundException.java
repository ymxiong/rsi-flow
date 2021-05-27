package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowCodeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 959898425615634332L;

    public FlowCodeNotFoundException() {
    }

    public FlowCodeNotFoundException(String message) {
        super(message);
    }

    public FlowCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowCodeNotFoundException(Throwable cause) {
        super(cause);
    }

    public FlowCodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
