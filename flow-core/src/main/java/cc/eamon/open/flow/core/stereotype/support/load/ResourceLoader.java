package cc.eamon.open.flow.core.stereotype.support.load;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.load.FlowLoader;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
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
public class ResourceLoader implements FlowLoader {
    @Override
    public Set<FlowConfig> load(String... configFiles) {
        Set<FlowConfig> flowConfigs = new HashSet<>();
        if (configFiles != null) {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            try {
                for (String configFile : configFiles) {
                    URL url = this.getClass().getClassLoader().getResource(configFile);
                    String jarPath = url.toString().substring(0, url.toString().indexOf("!/") + 2);
                    URL jarURL = new URL(jarPath);
                    JarURLConnection jarCon = (JarURLConnection) jarURL.openConnection();
                    JarFile jarFile = jarCon.getJarFile();
                    Enumeration<JarEntry> jarEntrys = jarFile.entries();

                    while (jarEntrys.hasMoreElements()) {
                        JarEntry entry = jarEntrys.nextElement();
                        String name = entry.getName();
                        String[] split = name.split("/");
                        String fileName=split[split.length-1];
                        if (fileName.startsWith("flow") && !entry.isDirectory()) {
                            Resource resource = new ClassPathResource(name);
                            String flowConfigJson = FileTools.inputStream2Str(resource.getInputStream());
                            FlowConfig flowConfig = JSONObject.parseObject(flowConfigJson, FlowConfig.class);
                            flowConfigs.add(flowConfig);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return flowConfigs;
    }
}
