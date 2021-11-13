package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowTypeNotSupportException extends RuntimeException {

    private static final long serialVersionUID = 959898425615634332L;

    public FlowTypeNotSupportException() {
        super("流程类型不支持");
    }

    public FlowTypeNotSupportException(String message) {
        super(message);
    }

    public FlowTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowTypeNotSupportException(Throwable cause) {
        super(cause);
    }

    public FlowTypeNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
