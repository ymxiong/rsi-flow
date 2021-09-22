package cc.eamon.open.flow.model.error;

import java.io.Serializable;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowModelNotMatchException extends RuntimeException {


    private static final long serialVersionUID = 5880312343222086336L;

    public FlowModelNotMatchException() {
    }

    public FlowModelNotMatchException(String message) {
        super(message);
    }

    public FlowModelNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowModelNotMatchException(Throwable cause) {
        super(cause);
    }

    public FlowModelNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
