package cc.eamon.open.flow.model.stereotype.pipeline;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.config.model.ModelFiledConfig;
import cc.eamon.open.flow.core.context.pipeline.BasePipeline;
import cc.eamon.open.flow.model.definition.FlowModel;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
                logger.info("Model named "+flowModel.getId()+ " has been loaded.");
            } else {
                queue.offer(value);
            }

        }
    }


    private FlowModel getModelFromConfig(ModelConfig config) {
        FlowModel model = null;
        if (config.getReference() != null) {
            model = getReferenceModel(config);
        } else {
            model=getUserDefinedModel(config);
        }
        return model;
    }


    private FlowModel getReferenceModel(ModelConfig config) {
        FlowModel model = new FlowModel();
        model.setId(config.getId());
        model.addbasicFiled(config.getId(), config.getReference());
        return model;
    }

    private FlowModel getUserDefinedModel(ModelConfig config) {
        FlowModel model = new FlowModel();
        model.setId(config.getId());
        for (ModelFiledConfig modelFiledConfig : config.getFields()) {
            String name = modelFiledConfig.getName();
            String type = modelFiledConfig.getType();
            Class clazz = BasicTypeEnums.getType(type);
            if (clazz != null) {
                //基础类型
                model.addbasicFiled(name, clazz.getName());
            } else {
                //复杂类型
                if (flowModelConfigs.get(type) != null) {
                    FlowModel referenceType = flowModels.get(type);
                    if (referenceType != null) {
                        model.addcomplexFiled(name, referenceType);
                    } else {
                        //还未加载则先加载对应类型，然后在加载该类型
                        // TODO：目前无法解决循环依赖的问题
                        return null;
                    }
                } else {
                    //报错类型未定义
                    throw new FlowModelNotDefinedException("FlowModel named " + type + " not defined.");
                }
            }
        }
        return model;
    }
}
