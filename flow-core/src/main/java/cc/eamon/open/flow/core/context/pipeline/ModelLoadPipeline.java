package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.model.enums.ModelLoaderEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 13:05
 */
public class ModelLoadPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ModelLoadPipeline.class);

    private Map<String, ModelConfig> flowModelConfigs;

    private String[] modelConfigFiles;

    public ModelLoadPipeline(int level, String[] modelConfigFiles, Map<String, ModelConfig> flowModelConfigs) {
        super(level);
        this.modelConfigFiles = modelConfigFiles;
        this.flowModelConfigs = flowModelConfigs;
    }

    @Override
    public void initializerSelf() {
        logger.info("执行ModelLoadPipeline组装过程");
        for (ModelLoaderEnums value : ModelLoaderEnums.values()) {
            Set<ModelConfig> models = value.getLoader().load(modelConfigFiles);
            if (models == null) continue;
            for (ModelConfig modelConfig : models) {
                flowModelConfigs.put(modelConfig.getId(), modelConfig);
            }
        }
    }
}
