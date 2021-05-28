package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class ResultNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public ResultNotExistException(String id, String scope, String name) {
        super("流程结果不存在" + "流程ID：" + id + " 域：" + scope + "结果名：" + name);
    }

    public ResultNotExistException(String message) {
        super(message);
    }

    public ResultNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNotExistException(Throwable cause) {
        super(cause);
    }

    public ResultNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
