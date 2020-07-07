package cc.eamon.open.flow.core.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.load.FlowLoader;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class ResourceLoader implements FlowLoader {
    @Override
    public Set<FlowConfig> load(String... configFiles) {
        Set<FlowConfig> flowConfigs=new HashSet<>();
        for (String configFile:configFiles) {
            if (configFiles != null) {
                for (File file : FileTools.readFiles(configFile)) {
                    if (file.getName().startsWith("flow")) {
                        String flowConfigJson = FileTools.loadFile(file);
                        FlowConfig flowConfig = JSONObject.parseObject(flowConfigJson, FlowConfig.class);
                        flowConfigs.add(flowConfig);
                    }
                }
            }
        }
        return flowConfigs;
    }
}
