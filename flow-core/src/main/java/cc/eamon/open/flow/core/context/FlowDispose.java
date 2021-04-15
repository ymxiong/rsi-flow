package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.config.RouteConfig;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.load.FlowLoaderEnums;
import cc.eamon.open.flow.core.stereotype.support.scan.FlowScannerEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 配置、代码装配
 * <p>
 * Author: lucas
 * Date: 2020/6/28
 * Email: lucas@eamon.cc
 */
@Component
public class FlowDispose {

    private static Logger logger = LoggerFactory.getLogger(FlowDispose.class);

    /**
     * 流程Map
     * 键为流程id
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
        logger.info("流程配置信息装配完成");
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
     * @param configFiles 读取流程引擎配置文件
     */
    private static void loadConfig(String... configFiles) {
        for (FlowLoaderEnums value : FlowLoaderEnums.values()) {
            // @Config自动扫描
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs == null) continue;
            flowConfigs.addAll(configs);
        }
    }


    /**
     * imp2. 扫描所有flow代码
     *
     * @param basePackages 扫描流程代码
     */
    private static void loadFlow(String... basePackages) {
        // 枚举扫描器
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
    private void assemblyFlow() {
        // TODO：对于不匹配的校验
        for (FlowConfig flowConfig : flowConfigs) {
            Map<String, FlowCode> flowCodes = new HashMap<>();
            for (RouteConfig routeConfig : flowConfig.getRoutes()) {
                //匹配调用的FlowCode
                if (flowCodeMap.get(routeConfig.getReference()) != null) {
                    flowCodes.put(routeConfig.getReference(), flowCodeMap.get(routeConfig.getReference()));
                }
            }
            FlowHolder flowHolder = new FlowHolder(flowConfig.getType(), flowConfig.getName(), flowConfig.getId(), null, flowConfig, flowCodes);
            flowHolders.put(flowHolder.getId(), flowHolder);
        }
    }


    public static FlowHolder getFlowHolderByName(String name) throws CloneNotSupportedException {
        FlowHolder flowHolder = null;
        try {
            flowHolder = flowHolders.get(name).clone();
        } catch (CloneNotSupportedException e) {
            throw e;
        }
        return flowHolder;
    }

    public static void setConfigBasePackages(String[] configBasePackages) {
        FlowDispose.configBasePackages = configBasePackages;
    }

    public static void setFlowBasePackages(String[] flowBasePackages) {
        FlowDispose.flowBasePackages = flowBasePackages;
    }
}
