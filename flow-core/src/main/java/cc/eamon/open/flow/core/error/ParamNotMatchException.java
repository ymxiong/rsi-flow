package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ParamNotMatchException extends FlowException{
    public ParamNotMatchException() {
    }

    public ParamNotMatchException(String message) {
        super(message);
    }

    public ParamNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNotMatchException(Throwable cause) {
        super(cause);
    }

    public ParamNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
