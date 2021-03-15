package cc.eamon.open.flow.core.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.load.FlowLoader;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class ResourceLoader implements FlowLoader {
    @Override
    public Set<FlowConfig> load(String... configFiles) {
        Set<FlowConfig> flowConfigs = new HashSet<>();
        if (configFiles != null) {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            for (String configFile : configFiles) {
                for (File file : FileTools.readFiles(path + configFile)) {
//                for (File file : FileTools.readFiles(configFile)) {
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
