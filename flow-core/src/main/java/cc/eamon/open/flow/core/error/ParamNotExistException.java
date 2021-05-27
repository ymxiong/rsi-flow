package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ParamNotExistException extends FlowException {

    private static final long serialVersionUID = -6528089647923156533L;

    public ParamNotExistException() {
        super("流程参数不存在错误");
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
