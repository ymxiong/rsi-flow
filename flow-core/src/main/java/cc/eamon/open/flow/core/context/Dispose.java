package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.load.FlowLoaderEnums;
import cc.eamon.open.flow.core.stereotype.support.scan.FlowScannerEnums;

import java.util.*;

/**
 * @author: lucas
 * @date: 2020/6/28
 * @email: lucas@eamon.cc
 */
public class Dispose {

    private Map<String, FlowHolder> flowHolders = new LinkedHashMap<>();

    private List<FlowConfig> flowConfigs = new ArrayList<>();

    private Map<String, FlowCode> flowCodeMap = new LinkedHashMap<>();
    

    public Dispose() {
        // 1. 扫描所有flow配置
        // 2. 扫描所有flow代码
        // 3. 装配flow
        String[] configFiles={Dispose.class.getClassLoader().getResource("").getPath()};
        loadConfig(configFiles);

        String[] basePackages={"cc.eamon.open.flow.example"};
        loadFlow(basePackages);
        assemblyFlow();
    }

    public Map<String, FlowHolder> getFlowHolders() {
        return flowHolders;
    }

    /**
     * imp1.扫描所有flow配置
     * @param configFiles
     * @return
     */
    public void loadConfig(String... configFiles){
        for (FlowLoaderEnums value:FlowLoaderEnums.values()){
            Set<FlowConfig> configs = value.getLoader().load(configFiles);
            if (configs==null) continue;
            for (FlowConfig config:configs){
                flowConfigs.add(config);
            }
        }
    }



    /**
     * imp2. 扫描所有flow代码
     * @param basePackages
     * @return
     */
    public void loadFlow(String... basePackages){
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
    public void assemblyFlow(){
        // TODO：对于不匹配的校验
        for (FlowConfig flowConfig : flowConfigs) {
            //List<FlowCode> flowCodes = new ArrayList<>();
            Map<String, FlowCode> flowCodes=new HashMap<>();
            for (RouteConfig routeConfig : flowConfig.getRoutes()) {
                if (flowCodeMap.get(routeConfig.getName()) != null) {
                    //flowCodes.add(flowCodeMap.get(routeConfig.getName()));
                    flowCodes.put(routeConfig.getName(),flowCodeMap.get(routeConfig.getName()));
                }
                //TODO:子流程匹配装配

            }
            FlowHolder flowHolder = new FlowHolder(flowConfig, flowCodes);
            flowHolders.put(flowHolder.getFlowName(), flowHolder);
        }
    }







}
