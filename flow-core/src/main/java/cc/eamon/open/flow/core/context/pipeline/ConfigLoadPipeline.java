package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.stereotype.support.enums.LoaderEnums;

import java.util.List;
import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:04:41
 */
public class ConfigLoadPipeline extends BasePipeline {

    private List<FlowConfig> flowConfigs;

    private String[] configFiles;

    public ConfigLoadPipeline(Pipeline pre, List<FlowConfig> flowConfigs, String... configFiles) {
        super(pre);
        this.flowConfigs = flowConfigs;
        this.configFiles = configFiles;
    }

    @Override
    public void initializerSelf() {
        for (LoaderEnums value : LoaderEnums.values()) {
            // @Config自动扫描
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs == null) continue;
            flowConfigs.addAll(configs);
        }
    }
}
