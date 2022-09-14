package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotMatchException;
import com.alibaba.fastjson.JSON;

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

    public FlowUserDefineModel(String id, String targetReference) {
        this.id = id;
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
    public void check(Object variable) {
    }

    @Override
    public Object parse(Object variable) {
        if (variable instanceof Map) {
            fields.forEach((k, v) -> {
                Map<String, Object> variableMap = (Map<String, Object>) variable;
                Object value = (variableMap).get(k);
                if (value == null) {
                    throw new FlowModelNotMatchException();
                }
                variableMap.put(k, v.parse(value));
            });
        }
        check(variable);
        return variable;
    }

    @Override
    public Object build(String name, String modelType, Map<String, Object> variables) {
        Map<String, Object> modelMap = new HashMap<>(fields.size());
        for (Map.Entry<String, FlowModel> modelEntry : fields.entrySet()) {
            FlowModel fieldModel = modelEntry.getValue();
            String fieldName = modelEntry.getKey();
            Object existModelVariables = variables.get(fieldName);
            if (existModelVariables != null) {
                modelMap.put(fieldName, existModelVariables);
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
