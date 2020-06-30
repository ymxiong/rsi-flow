package cc.eamon.open.flow.core.context;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: lucas
 * @date: 2020/6/28
 * @email: lucas@eamon.cc
 */
public class DisposeContext {

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
