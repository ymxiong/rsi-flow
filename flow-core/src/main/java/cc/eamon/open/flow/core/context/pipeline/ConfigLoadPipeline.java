package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.stereotype.support.enums.LoaderEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:04:41
 */
public class ConfigLoadPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ConfigLoadPipeline.class);

    private List<FlowConfig> flowConfigs;

    private String[] configFiles;

    public ConfigLoadPipeline(int level,List<FlowConfig> flowConfigs, String... configFiles) {
        super(level);
        this.flowConfigs = flowConfigs;
        this.configFiles = configFiles;
    }

    @Override
    public void initializerSelf() {
        logger.info("执行ConfigLoadPipeline组装过程");
        for (LoaderEnums value : LoaderEnums.values()) {
            // @Config自动扫描
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs == null) continue;
            flowConfigs.addAll(configs);
        }
    }
}
