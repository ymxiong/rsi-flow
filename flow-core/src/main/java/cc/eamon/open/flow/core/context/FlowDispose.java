package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.load.FlowLoaderEnums;
import cc.eamon.open.flow.core.stereotype.support.scan.FlowScannerEnums;

import java.util.*;

/**
 * 配置、代码装配
 * <p>
 * Author: lucas
 * Date: 2020/6/28
 * Email: lucas@eamon.cc
 */
public class FlowDispose {

    /**
     * 流程Map
     * 键为流程名称
     * 值为该流程的Holder,其中包含了流程的配置信息以及流程所需要的流程代码配置
     */
    private static Map<String, FlowHolder> flowHolders = new LinkedHashMap<>();

    /**
     * 流程配置键值
     */
    private static List<FlowConfig> flowConfigs = new ArrayList<>();

    /**
     * 流程代码键值Map
     * 键为流程代码的名称
     * 值为对应的流程代码
     */
    private static Map<String, FlowCode> flowCodeMap = new LinkedHashMap<>();


    /**
     * 流程配置所在路径
     */
    private static String[] configBasePackages;


    /**
     * 流程代码所在路径
     */
    private static String[] flowBasePackages;

    public static boolean flowEnv = Boolean.parseBoolean(System.getProperty("flowEnv", "false"));

    public FlowDispose() {
        if (flowEnv) {
            //测试阶段设置配置扫描路径与代码扫描路径
            configBasePackages = new String[]{""};
            flowBasePackages = new String[]{"cc.eamon.open.flow.example"};
        }
        // 1. 扫描所有flow配置
        loadConfig(configBasePackages);
        // 2. 扫描所有flow代码
        loadFlow(flowBasePackages);
        // 3. 装配flow
        assemblyFlow();
    }

    public static void test() {
        flowEnv = true;
    }

    public Map<String, FlowHolder> getFlowHolders() {
        return flowHolders;
    }

    /**
     * imp1.扫描所有flow配置
     *
     * @param configFiles
     * @return
     */
    public static void loadConfig(String... configFiles) {
        for (FlowLoaderEnums value : FlowLoaderEnums.values()) {
            // @Config自动扫描
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs == null) continue;
            for (FlowConfig config : configs) {
                flowConfigs.add(config);
            }
        }
    }


    /**
     * imp2. 扫描所有flow代码
     *
     * @param basePackages
     * @return
     */
    public static void loadFlow(String... basePackages) {
        for (FlowScannerEnums value : FlowScannerEnums.values()) {
            // @FlowScan自动扫描
            Set<? extends FlowCode> flowCodes = value.getScanner().scan(basePackages);
            if (flowCodes == null) continue;
            for (FlowCode flowCode : flowCodes) {
                flowCodeMap.put(flowCode.getFlowName(), flowCode);
            }
        }
    }

    /**
     * 根据配置装配flow, 装配过程放入flow holder
     */
    public void assemblyFlow() {
        // TODO：对于不匹配的校验
        for (FlowConfig flowConfig : flowConfigs) {
            Map<String, FlowCode> flowCodes = new HashMap<>();
            for (RouteConfig routeConfig : flowConfig.getRoutes()) {
                if (flowCodeMap.get(routeConfig.getName()) != null) {
                    flowCodes.put(routeConfig.getName(), flowCodeMap.get(routeConfig.getName()));
                }
            }
            FlowHolder flowHolder = new FlowHolder(flowConfig.getType(), flowConfig.getName(), flowConfig.getId(), null, flowConfig, flowCodes);
            flowHolders.put(flowHolder.getName(), flowHolder);
        }
    }


    public static FlowHolder getFlowHolderByName(String name) {
        return flowHolders.get(name);
    }

    public static void setConfigBasePackages(String[] configBasePackages) {
        FlowDispose.configBasePackages = configBasePackages;
    }

    public static void setFlowBasePackages(String[] flowBasePackages) {
        FlowDispose.flowBasePackages = flowBasePackages;
    }
}
