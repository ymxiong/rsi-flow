package cc.eamon.open.flow.core.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.config.enums.ConfigEnums;
import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowConfigLoadException;
import cc.eamon.open.flow.core.stereotype.load.FlowLoader;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class ResourceLoader implements FlowLoader {

    private static Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    @Override
    public Set<FlowConfig> load(String... configFiles) {
        Set<FlowConfig> flowConfigs = new HashSet<>();
        URL flow = this.getClass().getClassLoader().getResource("flow");
        if (flow == null) {
            logger.info("resource包中未找到名为flow的config包");
            return flowConfigs;
        }
        String path = flow.getPath();
        if (configFiles == null) return flowConfigs;
        for (String configFile : configFiles) {
            for (File file : FileTools.readFiles(path + configFile)) {
                for (ConfigEnums value : ConfigEnums.values()) {
                    try {
                        if (file.getName().startsWith(value.getName())) {
                            String flowConfigJson = FileTools.loadFile(file);
                            FlowConfig flowConfig = JSONObject.parseObject(flowConfigJson, FlowConfig.class);
                            if (flowConfig == null) continue;
                            flowConfigs.add(flowConfig);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new FlowConfigLoadException(file.getName());
                    }
                }
            }
        }
        return flowConfigs;
    }
}
