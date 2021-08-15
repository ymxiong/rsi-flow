package cc.eamon.open.flow.model.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.model.stereotype.load.FlowModelLoader;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class JarLoader implements FlowModelLoader {
    private static Logger logger = LoggerFactory.getLogger(JarLoader.class);

    @Override
    public Set<ModelConfig> load(String... configFiles) {
        Set<ModelConfig> modelConfigs = new HashSet<>();
        if (configFiles != null) {
            try {
                URL url = this.getClass().getClassLoader().getResource("flow");
                if (url == null) {
                    logger.info("jar包中未找到名为model的model包");
                    return modelConfigs;
                }
                String jarPath = url.toString().substring(0, url.toString().indexOf("!/") + 2);
                URL jarURL = new URL(jarPath);
                JarURLConnection jarCon = (JarURLConnection) jarURL.openConnection();
                JarFile jarFile = jarCon.getJarFile();
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry entry = jarEntries.nextElement();
                    String name = entry.getName();
                    String[] split = name.split("/");
                    String fileName = split[split.length - 1];
                    if (fileName.startsWith("model-") && !entry.isDirectory()) {
                        Resource resource = new ClassPathResource(name);
                        String flowModelJson = FileTools.inputStream2Str(resource.getInputStream());
                        ModelConfig modelConfig = JSONObject.parseObject(flowModelJson, ModelConfig.class);
                        if (modelConfig == null) {
                            logger.info("Please rename the file which named" + fileName);
                            continue;
                        }
                        modelConfigs.add(modelConfig);
                    }
                }


            } catch (Exception e) {
//                e.printStackTrace();
                logger.info(e.getMessage());
            }
        }
        return modelConfigs;
    }
}
