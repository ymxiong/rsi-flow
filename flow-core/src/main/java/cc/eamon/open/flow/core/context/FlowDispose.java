package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
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

    private static Map<String, FlowHolder> flowHolders = new LinkedHashMap<>();

    private static List<FlowConfig> flowConfigs = new ArrayList<>();

    private static Map<String, FlowCode> flowCodeMap = new LinkedHashMap<>();

    private static String[] configBasePackages;

    private static String[] flowBasePackages;

    public FlowDispose() {
        // 1. 扫描所有flow配置
        loadConfig(configBasePackages);
        // 2. 扫描所有flow代码
        loadFlow(flowBasePackages);
        // 3. 装配flow
        assemblyFlow();
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
            // TODO: NEED OPTIMIZE @FlowScan
            // EXAMPLE: @ComponentScan @MapperScan
            // 往后排
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
