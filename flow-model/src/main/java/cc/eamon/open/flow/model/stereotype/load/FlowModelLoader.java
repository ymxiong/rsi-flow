package cc.eamon.open.flow.model.stereotype.load;


import cc.eamon.open.flow.config.model.ModelConfig;

import java.util.Set;

public interface FlowModelLoader {
    /**
     * @param configFiles 流程配置文件
     * @return 流程配置组
     */
    Set<ModelConfig> load(String... configFiles);
}
