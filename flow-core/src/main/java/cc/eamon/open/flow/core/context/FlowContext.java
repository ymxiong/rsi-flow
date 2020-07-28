package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.FlowEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 00:48:38
 */
public class FlowContext {

    /**
     * 当前线程流程引擎
     */
    private FlowEngine flowEngine;
    /**
     * 当前线程流程名称
     */
    private String flowName;
    /**
     * 当前线程参数列表
     */
    private Map<String, Object> variableMap = new HashMap<>();
    /**
     * 当前线程参数类型
     */
    private Map<String, Class> variableTypeMap = new HashMap<>();

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    protected Map<String, Object> getVariableMap() {
        return variableMap;
    }

    protected Map<String, Class> getVariableTypeMap() {

        return variableTypeMap;
    }

    public Object retrieveVariable(String variableName) {
        return variableMap.get(variableName);
    }

    public Class retrieveVariableType(String variableName) {
        return variableTypeMap.get(variableName);
    }

    public void registerVariable(String variableName, Object variableValue, Class variableType) {
        variableMap.put(variableName, variableValue);
        variableTypeMap.put(variableName, variableType);
    }

    public void registerVariableList(Map<String, Object> variableMap, Map<String, Class> variableTypeMap) {
        this.variableMap.putAll(variableMap);
        this.variableTypeMap.putAll(variableTypeMap);
    }

    public void registerChildVariable(FlowContextExtractor childContext) {
        //注入参数到上下文中
        if (childContext != null)
            FlowContextHolder.get().registerVariableList(childContext.getVariableMap(), childContext.getVariableTypeMap());

    }

    //TODO:测试
    public void releaseVariable(String... variableNames) {
        if (variableNames != null) {
            for (String variableName : variableNames) {
                variableMap.remove(variableName);
                variableTypeMap.remove(variableName);
            }
        }

    }


    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

}
