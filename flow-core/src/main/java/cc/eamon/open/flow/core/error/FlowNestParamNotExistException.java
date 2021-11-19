package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class FlowNestParamNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6528089647923156533L;

    public FlowNestParamNotExistException(String flowNodeId,String scope,String name) {
        super("嵌套调用流程时参数确实" + ",流程节点ID:" + flowNodeId+",参数域:" + scope+",参数名ID:" + name);

    }

    public FlowNestParamNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNestParamNotExistException(Throwable cause) {
        super(cause);
    }

    public FlowNestParamNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
