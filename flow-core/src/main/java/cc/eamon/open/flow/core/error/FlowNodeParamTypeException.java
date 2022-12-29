package cc.eamon.open.flow.core.error;

public class FlowNodeParamTypeException extends RuntimeException {

    private static final long serialVersionUID = -1092939811366593156L;

    public FlowNodeParamTypeException(String flowNodeId, String scope, String paramName) {
        super("流程节点参数类型不匹配" + ", 流程节点ID：" + flowNodeId + ", 域：" + scope + ", 参数名：" + paramName);
    }

    public FlowNodeParamTypeException(String flowNodeId, String scope, String paramName, String message) {
        super(message);
    }

    public FlowNodeParamTypeException(String paramName, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNodeParamTypeException(String paramName, Throwable cause) {
        super(cause);
    }

    public FlowNodeParamTypeException(String paramName, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
