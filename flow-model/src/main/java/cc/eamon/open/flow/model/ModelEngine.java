package cc.eamon.open.flow.model;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.model.definition.FlowModel;
import cc.eamon.open.flow.model.definition.FlowReferenceModel;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ModelEngine {

    private static Logger logger = LoggerFactory.getLogger(ModelEngine.class);
    /**
     * 用于存储流程模型
     */
    public static Map<String, FlowModel> flowModels = new HashMap<>();

    /**
     *
     */
    public static Map<String, ModelConfig> flowModelConfigs = new HashMap<>();

    public static void setFlowModels(Map<String, FlowModel> flowModels) {
        ModelEngine.flowModels = flowModels;
    }

    public static Object check(String modelType, Object object) {
        // STEP 1:根据modelType构建出FlowModel
        FlowModel flowModel = flowModels.get(modelType);
        if (flowModel == null) {
            Class clazz = BasicTypeEnums.getType(modelType);
            if (clazz != null) {
                // 可能是reference类型,但是没有写JSON未加载,在此处加载
                flowModel = new FlowReferenceModel(clazz.getSimpleName(), clazz.getName(), clazz);
            } else {
                throw new FlowModelNotDefinedException();
            }
        }
        // STEP 2:根据FlowModel的类型进行不同的校验
        object = flowModel.check(object);
        return object;
    }

}
