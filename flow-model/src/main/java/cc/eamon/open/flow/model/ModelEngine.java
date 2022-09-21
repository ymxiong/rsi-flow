package cc.eamon.open.flow.model;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.model.definition.FlowModel;
import cc.eamon.open.flow.model.definition.FlowParameterizeModel;
import cc.eamon.open.flow.model.definition.FlowReferenceModel;
import cc.eamon.open.flow.model.definition.parameterize.support.ParameterizeModelType;
import cc.eamon.open.flow.model.definition.parameterize.util.ParameterizeModelUtils;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotDefinedException;
import cc.eamon.open.flow.model.error.FlowModelParameterizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ModelEngine {

    private static final Logger logger = LoggerFactory.getLogger(ModelEngine.class);
    /**
     * 用于存储流程模型
     */
    public static Map<String, FlowModel> flowModels = new HashMap<>();

    /**
     * 用于存储模型配置
     */
    public static Map<String, ModelConfig> flowModelConfigs = new HashMap<>();

    public static void setFlowModels(Map<String, FlowModel> flowModels) {
        ModelEngine.flowModels = flowModels;
    }

    public static Object build(String name, String modelType, Object object, Map<String, Object> variables) {
        // STEP 1:根据modelType构建出FlowModel
        FlowModel flowModel = flowModels.get(modelType);
        if (flowModel == null) {
            if (modelType.contains("(") && modelType.contains(")") && ParameterizeModelUtils.isMatchParameterize(modelType)) {
                String mainClassType = modelType.substring(0, modelType.indexOf("("));
                Class mainClass = BasicTypeEnums.getType(mainClassType);
                if (mainClass == null) throw new FlowModelParameterizeException("Class not found:" + mainClassType);
                // 解析范型type
                flowModel = new FlowParameterizeModel(modelType, mainClassType, mainClass, null, new ParameterizeModelType(modelType, modelType.substring(0, modelType.indexOf("("))).handle());
            } else {
                Class clazz = BasicTypeEnums.getType(modelType);
                if (clazz != null) {
                    // 可能是reference类型,但是没有写JSON未加载,在此处加载
                    flowModel = new FlowReferenceModel(clazz.getName(), clazz.getName(), clazz);
                } else {
                    throw new FlowModelNotDefinedException("FlowModel named " + modelType + " not found. Check your json file.");
                }
            }
            flowModels.put(modelType, flowModel);
        }
        // STEP 2:根据FlowModel的类型进行不同的构建
        object = flowModel.parse(object);
        object = flowModel.check(object);
        if (object == null) object = flowModel.build(name, modelType, variables);
        return object;
    }

}
