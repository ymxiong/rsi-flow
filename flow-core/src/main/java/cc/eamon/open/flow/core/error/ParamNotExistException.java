package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ParamNotExistException extends FlowException{
    public ParamNotExistException() {
    }

    public ParamNotExistException(String message) {
        super(message);
    }

    public ParamNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNotExistException(Throwable cause) {
        super(cause);
    }

    public ParamNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
