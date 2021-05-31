package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ParamNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public ParamNotExistException(String paramName) {
        super("流程参数不存在错误: " + paramName);
    }

    public ParamNotExistException(String paramName, String message) {
        super(message);
    }

    public ParamNotExistException(String paramName, String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNotExistException(String paramName, Throwable cause) {
        super(cause);
    }

    public ParamNotExistException(String paramName, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
