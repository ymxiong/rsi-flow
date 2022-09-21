package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.config.model.ModelFiledConfig;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowUserDefineModel implements FlowModel {

    private final String id;

    private final String targetReference;

    private Class<?> targetReferenceClazz;

    private ModelConfig modelConfig;

    public FlowUserDefineModel(String id, ModelConfig modelConfig, String targetReference) {
        this.id = id;
        this.modelConfig = modelConfig;
        this.targetReference = targetReference;
        if (targetReference != null) {
            this.targetReferenceClazz = BasicTypeEnums.getType(targetReference);
        }
    }

    /**
     * key:field模型名称 value:模型
     */
    private final Map<String, FlowModel> fields = new HashMap<>();

    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return FlowModelTypeEnums.FLOW_USER_DEFINE_MODEL.getType();
    }

    public String getTargetReference() {
        return targetReference;
    }

    public Class<?> getTargetReferenceClazz() {
        return targetReferenceClazz;
    }

    public void addField(String name, FlowModel flowModel) {
        fields.put(name, flowModel);
    }


    @Override
    public Object check(Object variable) {
        // 基于Map作校验，否则没法取出准确实体，或者序列化工具会造成类型转化混乱
        Map<String, Object> checkVariable = JSONObject.parseObject(JSON.toJSONString(variable));
        for (ModelFiledConfig field : modelConfig.getFields()) {
            String fieldName = field.getName();
            checkVariable.put(fieldName, fields.get(fieldName).check(checkVariable.get(fieldName)));
        }
        if (targetReference != null && targetReferenceClazz != null) {
            return JSON.parseObject(JSON.toJSONString(checkVariable), targetReferenceClazz);
        }
        return checkVariable;
    }

    @Override
    public Object parse(Object variable) {
        if (variable instanceof Map) {
            fields.forEach((k, v) -> {
                Map<String, Object> variableMap = (Map<String, Object>) variable;
                Object value = (variableMap).get(k);
                variableMap.put(k, v.check(v.parse(value)));
            });
            if (targetReference != null && targetReferenceClazz != null) {
                return JSON.parseObject(JSON.toJSONString(variable), targetReferenceClazz);
            } else return variable;
        } else {
            return variable;
        }
    }

    @Override
    public Object build(String name, String modelType, Map<String, Object> variables) {
        Map<String, Object> modelMap = new HashMap<>(fields.size());
        for (Map.Entry<String, FlowModel> modelEntry : fields.entrySet()) {
            FlowModel fieldModel = modelEntry.getValue();
            String fieldName = modelEntry.getKey();
            Object existModelVariable = variables.get(fieldName);
            if (existModelVariable != null) {
                modelMap.put(fieldName, existModelVariable);
            } else {
                modelMap.put(fieldName, fieldModel.build(fieldName, fieldModel.getType(), variables));
            }
        }
        if (targetReference != null && targetReferenceClazz != null) {
            return JSON.parseObject(JSON.toJSONString(modelMap), targetReferenceClazz);
        } else {
            return modelMap;
        }
    }
}
