package cc.eamon.open.flow.model.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.config.enums.ConfigEnums;
import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.model.stereotype.load.FlowModelLoader;
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
public class ResourceLoader implements FlowModelLoader {

    private static Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    @Override
    public Set<ModelConfig> load(String... modelFiles) {
        Set<ModelConfig> modelConfigs = new HashSet<>();
        URL flow = this.getClass().getClassLoader().getResource("model");
        if (flow == null) {
            logger.info("resource包中未找到名为model的model包");
            return modelConfigs;
        }
        String path = flow.getPath();
        if (modelFiles == null) return modelConfigs;
        for (String modelFile : modelFiles) {
            for (File file : FileTools.readFiles(path + modelFile)) {
                for (ConfigEnums value : ConfigEnums.values()) {
                    if (file.getName().startsWith(value.getName())) {
                        String flowConfigJson = FileTools.loadFile(file);
                        ModelConfig modelConfig = JSONObject.parseObject(flowConfigJson, ModelConfig.class);
                        if (modelConfig == null) continue;
                        modelConfigs.add(modelConfig);
                    }

                }
            }
        }
        return modelConfigs;
    }
}
