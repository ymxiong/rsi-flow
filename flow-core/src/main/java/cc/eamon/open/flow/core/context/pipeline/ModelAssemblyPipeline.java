package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.config.model.ModelFiledConfig;
import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.model.definition.FlowModel;
import cc.eamon.open.flow.model.definition.FlowReferenceModel;
import cc.eamon.open.flow.model.definition.FlowUserDefineModel;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 13:05
 */
public class ModelAssemblyPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ModelAssemblyPipeline.class);

    private Map<String, ModelConfig> flowModelConfigs;

    private Map<String, FlowModel> flowModels;

    public ModelAssemblyPipeline(int level, Map<String, ModelConfig> flowModelConfigs, Map<String, FlowModel> flowModels) {
        super(level);
        this.flowModelConfigs = flowModelConfigs;
        this.flowModels = flowModels;
    }

    @Override
    public void initializerSelf() {
        Queue<ModelConfig> queue = new LinkedList<>();
        for (ModelConfig value : flowModelConfigs.values()) {
            queue.offer(value);

        }
        while (queue.peek() != null) {
            ModelConfig value = queue.poll();
            FlowModel flowModel = getModelFromConfig(value);
            if (flowModel != null) {
                flowModels.put(flowModel.getId(), flowModel);
                logger.info("Model named " + flowModel.getId() + " has been loaded.");
            } else {
                queue.offer(value);
            }
        }
        ModelEngine.setFlowModels(flowModels);
    }


    private FlowModel getModelFromConfig(ModelConfig config) {
        FlowModel model = null;
        if (config.getReference() != null) {
            model = getReferenceModel(config);
        } else {
            model = getUserDefinedModel(config);
        }
        return model;
    }


    private FlowModel getReferenceModel(ModelConfig config) {
        Class clazz = BasicTypeEnums.getType(config.getReference());
        FlowReferenceModel model = new FlowReferenceModel(config.getId(), config.getReference(), clazz);
        return model;
    }

    private FlowModel getUserDefinedModel(ModelConfig config) {
        FlowUserDefineModel model = new FlowUserDefineModel(config.getId());
        for (ModelFiledConfig modelFiledConfig : config.getFields()) {
            String name = modelFiledConfig.getName();
            String type = modelFiledConfig.getType();
            // STEP 1:如果Type里面有.的话那必定是引用
            // STEP 2:先尝试加载引用类型
            // STEP 3:加载引用类型失败后尝试加载用户自定义模型
            Class clazz = BasicTypeEnums.getType(type);
            if (clazz != null) {
                //基础类型
                FlowReferenceModel referenceModel = (FlowReferenceModel) flowModels.get(type);
                if (referenceModel == null) {
                    referenceModel = new FlowReferenceModel(clazz.getSimpleName(), clazz.getName(), clazz);
                }
                model.addField(name, referenceModel);
            } else {
                //复杂类型
                if (flowModelConfigs.get(type) != null) {
                    FlowModel fieldModel = flowModels.get(type);
                    if (fieldModel != null) {
                        model.addField(name, fieldModel);
                    } else {
                        //还未加载则先加载对应类型，然后在加载该类型
                        // TODO：目前无法解决循环依赖的问题
                        return null;
                    }
                } else {
                    //报错类型未定义
                    logger.info("FlowModel named " + type + " not defined.");
                    throw new FlowModelNotDefinedException("FlowModel named " + type + " not defined.");
                }
            }
        }
        return model;
    }
}
