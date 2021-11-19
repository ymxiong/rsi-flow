package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNodeRefMissingException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowNodeRefMissingException(String flowNodeId) {
        super("流程节点未标明Reference" + ",流程节点ID:" + flowNodeId);

    }

    public FlowNodeRefMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNodeRefMissingException(Throwable cause) {
        super(cause);
    }

    public FlowNodeRefMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
