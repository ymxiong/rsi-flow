package cc.eamon.open.flow.core.error;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 17:11:27
 */
public class MethodInvokeException extends RuntimeException {

    private static final long serialVersionUID = 7779832145594507914L;

    public MethodInvokeException() {
    }

    public MethodInvokeException(String message) {
        super(message);
    }

    public MethodInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodInvokeException(Throwable cause) {
        super(cause);
    }

    public MethodInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
