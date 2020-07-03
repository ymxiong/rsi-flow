package cc.eamon.open.flow.core.context;


import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.FlowItemConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.scan.FlowScannerEnums;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author: lucas
 * @date: 2020/6/28
 * @email: lucas@eamon.cc
 */
public class DisposeContext {

    private Map<String, FlowHolder> flowHolders = new LinkedHashMap<>();

    public DisposeContext() {
        // 1. 扫描所有flow配置
        // 2. 扫描所有flow代码
        // 3. 装配flow

        // imp1.扫描所有flow配置
        List<FlowConfig> flowConfigs = new ArrayList<>();

        URL resource = DisposeContext.class.getClassLoader().getResource("");
        if (resource != null) {
            for (File file : FileTools.readFiles(resource.getPath())) {
                if (file.getName().startsWith("flow")) {
                    String flowConfigJson = FileTools.loadFile(file);
                    FlowConfig flowConfig = JSONObject.parseObject(flowConfigJson, FlowConfig.class);
                    flowConfigs.add(flowConfig);
                }
            }
        }

        // imp2. 扫描所有flow代码
        Map<String, FlowCode> flowCodeMap = new LinkedHashMap<>();
        for (FlowScannerEnums value : FlowScannerEnums.values()) {
            Set<? extends FlowCode> flowCodes = value.getScanner().scan("cc.eamon.open.flow.example");
            if (flowCodes == null) continue;
            for (FlowCode flowCode : flowCodes) {
                flowCodeMap.put(flowCode.getFlowName(), flowCode);
            }
        }


        // imp3. 根据配置装配flow, 装配过程放入flow holder
        // TODO：对于不匹配的校验
        for (FlowConfig flowConfig : flowConfigs) {
            List<FlowCode> flowCodes = new ArrayList<>();
            for (FlowItemConfig flow : flowConfig.getFlows()) {
                if (flowCodeMap.get(flow.getName()) != null) {
                    flowCodes.add(flowCodeMap.get(flow.getName()));
                }
            }
            FlowHolder flowHolder = new FlowHolder(flowConfig, flowCodes);
            flowHolders.put(flowHolder.getFlowName(), flowHolder);
        }
    }

    public Map<String, FlowHolder> getFlowHolders() {
        return flowHolders;
    }

    // =======================Deprecated========================


    private static String flowName;

    /**
     * 基础业务节点名称列表
     */
    private static List<String> nodeList = new LinkedList<>();

    /**
     * 基础业务节点名称方法Map
     */
    private static Map<String, Method> nodeMap = new HashMap<>();



    public static String getFlowName() {
        return flowName;
    }

    public static List<String> getNodeList() {
        return nodeList;
    }

    public static Map<String, Method> getNodeMap() {
        return nodeMap;
    }


    public static void setFlowName(String flowName) {
        DisposeContext.flowName = flowName;
    }

    public static void setNodeList(List<String> nodeList) {
        DisposeContext.nodeList = nodeList;
    }

    public static void setNodeMap(Map<String, Method> nodeMap) {
        DisposeContext.nodeMap = nodeMap;
    }



}
