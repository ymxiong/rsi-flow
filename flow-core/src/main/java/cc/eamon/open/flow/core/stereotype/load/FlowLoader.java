package cc.eamon.open.flow.core.stereotype.load;


import cc.eamon.open.flow.config.FlowConfig;

import java.util.Set;

public interface FlowLoader {
    /**
     * @param configFiles
     * @return
     */
    Set<FlowConfig> load(String... configFiles);
}
