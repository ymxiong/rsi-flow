package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.element.FlowDetail;
import cc.eamon.open.flow.core.element.RouteDetail;

import javax.lang.model.element.ExecutableElement;
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
     * 记录业务流程开始节点，用于提供业务流程入口
     */
    private static FlowDetail startNode;
    /**
     * 记录当前业务节点，用于封装业务流程顺序
     */
    private static FlowDetail curNode;

    /**
     * 基础业务节点名称列表
     */
    private static List<String> nodeList = new LinkedList<>();

    /**
     * 基础业务节点名称方法Map
     */
    private static Map<String, Method> nodeMap = new HashMap<>();

    /**
     * 路由跳转
     */
    private static Map<String, RouteDetail> routeMap = new HashMap<>();


    public static String getFlowName() {
        return flowName;
    }

    public static List<String> getNodeList() {
        return nodeList;
    }

    public static Map<String, Method> getNodeMap() {
        return nodeMap;
    }

    public static Map<String, RouteDetail> getRouteMap() {
        return routeMap;
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

    public static void setRouteMap(Map<String, RouteDetail> routeMap) {
        DisposeContext.routeMap = routeMap;
    }


    public static FlowDetail getStartNode() {
        return startNode;
    }

    public static void setStartNode(FlowDetail startNode) {
        DisposeContext.startNode = startNode;
        curNode=startNode;
    }

    public static FlowDetail getCurNode() {
        return curNode;
    }

    public static void setCurNode(FlowDetail curNode) {
        DisposeContext.curNode = curNode;
    }

    public static void addFlow(FlowDetail flowDetail){
        curNode.setNextNode(flowDetail);
        curNode=curNode.getNextNode();
    }
}
