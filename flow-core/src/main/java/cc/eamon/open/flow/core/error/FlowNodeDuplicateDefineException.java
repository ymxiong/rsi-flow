package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNodeDuplicateDefineException extends FlowException {

    private static final long serialVersionUID = 959898425615634332L;

    public FlowNodeDuplicateDefineException() {
    }

    public FlowNodeDuplicateDefineException(String message) {
        super(message);
    }

    public FlowNodeDuplicateDefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNodeDuplicateDefineException(Throwable cause) {
        super(cause);
    }

    public FlowNodeDuplicateDefineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
