package cc.eamon.open.flow.config.enums;

import cc.eamon.open.flow.config.CommonConfig;
import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.model.ModelConfig;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:56:34
 */
public enum ConfigEnums {

    FLOW("flow", FlowConfig.class),

    MODEL("model", ModelConfig.class),

    ;

    private String name;

    private Class<? extends CommonConfig> config;

    ConfigEnums(String name, Class<? extends CommonConfig> config) {
        this.name = name;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public Class<? extends CommonConfig> getConfig() {
        return config;
    }

    public ConfigEnums getConfigByName(String name) {
        for (ConfigEnums value : ConfigEnums.values()) {
            if (value.getName().equals(name)) return value;
        }
        return null;
    }

}
