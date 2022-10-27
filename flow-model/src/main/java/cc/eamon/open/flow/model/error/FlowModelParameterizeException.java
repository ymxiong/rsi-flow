package cc.eamon.open.flow.model.error;

public class FlowModelParameterizeException extends RuntimeException {

    private static final long serialVersionUID = 284662319634172118L;

    public FlowModelParameterizeException(String message) {
        super("流程模型参数异常：" + message);
    }
}
