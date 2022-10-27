package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.config.model.ModelFiledConfig;
import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelCheckException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowReferenceModel implements FlowModel {

    private static final Logger logger = LoggerFactory.getLogger(FlowReferenceModel.class);

    private final String id;

    private String reference;

    private Class referenceClazz;

    private FlowModel flowModelReference;

    private ModelFiledConfig modelFiledConfig;

    public FlowReferenceModel(String id, String reference, Class referenceClazz, ModelFiledConfig modelFiledConfig) {
        this.id = id;
        this.reference = reference;
        this.referenceClazz = referenceClazz;
        this.modelFiledConfig = modelFiledConfig;
    }

    public FlowReferenceModel(String id, String reference, Class referenceClazz) {
        this.id = id;
        this.reference = reference;
        this.referenceClazz = referenceClazz;
    }

    public FlowReferenceModel(String id, FlowModel flowModelReference) {
        this.id = id;
        this.flowModelReference = flowModelReference;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return FlowModelTypeEnums.FLOW_REFERENCE_MODEL.getType();
    }

    @Override
    public Object check(Object variable) {
        if (this.flowModelReference != null) return flowModelReference.check(variable);
        if (variable == null) {
            if (modelFiledConfig != null) {
                if (modelFiledConfig.getNotnull() == null || modelFiledConfig.getNotnull())
                    throw new FlowModelCheckException(id, modelFiledConfig.getName(), null);
                else return modelFiledConfig.getDefaultValue();
            } else return null;
        } else if (JSONObject.class.isAssignableFrom(variable.getClass()) || !referenceClazz.isAssignableFrom(variable.getClass())) {
            // JSON的属性和类field的属性能否对应 || 是否是满足fastJSON序列化与反序列化标准
            try {
                return JSON.parseObject(JSON.toJSONString(variable), referenceClazz);
            } catch (Exception e) {
                throw new FlowModelCheckException(id, modelFiledConfig.getName(), variable);
            }
        }
        return variable;
    }

    @Override
    public Object parse(Object variable) {
        if (variable == null) return null;
        if (this.flowModelReference != null) return flowModelReference.parse(variable);
        if (referenceClazz != null && referenceClazz.isAssignableFrom(variable.getClass())) {
            return variable;
        } else if (Map.class.isAssignableFrom(variable.getClass())) {
            return JSONObject.parseObject(JSONObject.toJSONString(variable), referenceClazz);
        }
        // TODO: 2022/9/13 基础类型转换匹配
        return null;
    }

    @Override
    public Object build(String name, String modelType, Map<String, Object> variables) {
        if (this.flowModelReference != null) return flowModelReference.build(name, modelType, variables);
        return variables.get(name);
    }
}
