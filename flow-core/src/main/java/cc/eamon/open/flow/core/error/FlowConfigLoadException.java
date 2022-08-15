package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowConfigLoadException extends RuntimeException {

    private static final long serialVersionUID = 3607487848074751704L;

    public FlowConfigLoadException() {
    }

    public FlowConfigLoadException(String configFile) {
        super(configFile);
    }

    public FlowConfigLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowConfigLoadException(Throwable cause) {
        super(cause);
    }

    public FlowConfigLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
