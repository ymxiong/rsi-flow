package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.config.model.ModelFiledConfig;
import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelCheckException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class FlowParameterizeModel implements FlowModel {

    private static final Logger logger = LoggerFactory.getLogger(FlowParameterizeModel.class);

    private final String id;

    /**
     * type
     */
    private String reference;

    /**
     * raw type
     */
    private Class referenceClazz;

    /**
     * parameterize type
     */
    private Type type;

    private ModelFiledConfig modelFiledConfig;

    public FlowParameterizeModel(String id, String reference, Class referenceClazz, ModelFiledConfig modelFiledConfig, Type type) {
        this.id = id;
        this.reference = reference;
        this.referenceClazz = referenceClazz;
        this.modelFiledConfig = modelFiledConfig;
        this.type = type;
    }

    public FlowParameterizeModel(String id, String reference, Class referenceClazz, Type type) {
        this.id = id;
        this.reference = reference;
        this.referenceClazz = referenceClazz;
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return FlowModelTypeEnums.FLOW_PARAMETERIZE_MODEL.getType();
    }

    @Override
    public Object check(Object variable) {
        if (variable == null) {
            if (modelFiledConfig != null) {
                if (modelFiledConfig.getNotnull() == null || modelFiledConfig.getNotnull())
                    throw new FlowModelCheckException(id, modelFiledConfig.getName(), null);
                else return modelFiledConfig.getDefaultValue();
            } else return null;
        }
        return variable;
    }

    @Override
    public Object parse(Object variable) {
        try {
            return JSON.parseObject(JSON.toJSONString(variable), type, null);
        } catch (Exception e) {
            throw new FlowModelCheckException(id, modelFiledConfig.getName(), variable);
        }
    }

    @Override
    public Object build(String name, String modelType, Map<String, Object> variables) {
        return variables.get(name);
    }
}
