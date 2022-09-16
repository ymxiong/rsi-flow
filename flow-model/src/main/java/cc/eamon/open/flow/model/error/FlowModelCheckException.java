package cc.eamon.open.flow.model.error;

import com.alibaba.fastjson.JSON;

public class FlowModelCheckException extends RuntimeException {

    private static final long serialVersionUID = 8814174391010351489L;

    public FlowModelCheckException(String modelId, String fieldName, Object variable) {
        super("流程模型校验失败" + ", 模型ID：" + modelId + ", 模型参数名：" + fieldName + ", 参数：" + JSON.toJSONString(variable));
    }
}
