package cc.eamon.open.flow.core.check.context;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.stereotype.support.enums.LoaderEnums;

import java.util.*;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 这个类用于存储错误信息，比如参数覆盖（注入参数时之前该参数在上下文中已经存在）
 * 等不一定会引起错误的Error
 */
public class ConfigContext {

    /**
     * 流程配置所在路径
     */
    private static String configBasePackages;

    private static boolean isTest = false;

    /**
     * 流程配置键值
     */
    private Map<String, FlowConfig> flowConfigs = new HashMap<>();
    //TODO:首先需要读取到所需要的一系列配置文件
    //TODO:然后针对一个主的进行分析
    //TODO:分析过程
    //TODO:运用栈遍历得到所有执行路径
    //TODO:针对每一条路径执行校验，查看是否符合要求

    private FlowConfig mainFlow;

    private Map<String, FlowNodeConfig> routeConfigMap = new HashMap<>();


    public static String getConfigBasePackages() {
        return configBasePackages;
    }

    public static void setConfigBasePackages(String configBasePackages) {
        ConfigContext.configBasePackages = configBasePackages;
    }

    public static boolean isIsTest() {
        return isTest;
    }

    public static void setIsTest(boolean isTest) {
        ConfigContext.isTest = isTest;
    }

    public Map<String, FlowConfig> getFlowConfigs() {
        return flowConfigs;
    }

    public void setFlowConfigs(Map<String, FlowConfig> flowConfigs) {
        this.flowConfigs = flowConfigs;
    }

    public FlowConfig getMainFlow() {
        return mainFlow;
    }

    public void setMainFlow(FlowConfig mainFlow) {
        this.mainFlow = mainFlow;
    }

    public Map<String, FlowNodeConfig> getRouteConfigMap() {
        return routeConfigMap;
    }

    public void setRouteConfigMap(Map<String, FlowNodeConfig> routeConfigMap) {
        this.routeConfigMap = routeConfigMap;
    }

    public ConfigContext(String basePath) {
        if (isTest) {
            //测试阶段设置配置扫描路径
            configBasePackages = "";
        } else {
            configBasePackages = basePath;
        }
        // 1. 扫描所有flow配置
        loadConfig(configBasePackages);
    }


    public FlowNodeConfig getRouteById(String nodeId) {
        return routeConfigMap.get(nodeId);
    }


    /**
     * Step1. 预准备工作，读取所有的配置文件装进FLowConfig
     *
     * @param path
     */
    private void prepared(String path, String flowName) {
        loadConfig(path);
        mainFlow = flowConfigs.get(flowName);
//        for (FlowNodeConfig route : mainFlow.getRoutes()) {
//            routeConfigMap.put(route.getId(), route);
//        }
    }

    /**
     * Step1. 预准备工作，读取所有的配置文件装进FLowConfig
     *
     * @param path
     */
    private void loadConfig(String path) {
        for (LoaderEnums value : LoaderEnums.values()) {
            // @Config自动扫描
            Set<FlowConfig> configs = value.getLoader().load(path);
            if (configs == null) continue;
            for (FlowConfig config : configs) {
                flowConfigs.put(config.getId(), config);
            }
        }
    }


}
