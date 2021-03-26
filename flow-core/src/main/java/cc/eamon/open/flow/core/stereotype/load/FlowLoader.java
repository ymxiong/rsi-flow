package cc.eamon.open.flow.core.stereotype.load;


import cc.eamon.open.flow.config.FlowConfig;

import java.util.Set;

public interface FlowLoader {
    /**
     * @param configFiles 流程配置文件
     * @return 流程配置组
     */
    Set<FlowConfig> load(String... configFiles);
}
