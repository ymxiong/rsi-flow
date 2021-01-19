package cc.eamon.open.flow.core.check.context;

import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.support.load.FlowLoaderEnums;

import java.util.*;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 这个类用于存储错误信息，比如参数覆盖（注入参数时之前该参数在上下文中已经存在）
 *         等不一定会引起错误的Error
 */
public class ConfigContext {

    /**
     *  流程配置所在路径
     */
    private static String configBasePackages;

    private static boolean isTest=false;


    /**
     *  流程配置键值
     *
     */
    private static List<FlowConfig> flowConfigs = new ArrayList<>();

    public ConfigContext(String basePath) {
        if (isTest){
            //测试阶段设置配置扫描路径
            configBasePackages= "";
        }else {
            configBasePackages=basePath;
        }
        // 1. 扫描所有flow配置
        loadConfig(configBasePackages);
    }

    /**
     * 扫描所有flow配置
     *
     * @param configFiles
     * @return
     */
    public static void loadConfig(String... configFiles) {
        for (FlowLoaderEnums value : FlowLoaderEnums.values()) {
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs == null) continue;
            for (FlowConfig config : configs) {
                flowConfigs.add(config);
            }
        }
    }
}
