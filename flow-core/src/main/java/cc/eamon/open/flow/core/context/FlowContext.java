package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.FlowConstant;
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
     * 当前线程参数列表(多Scope)
     */
    private Map<String, Map<String, Object>> variableMap = new HashMap<>();
    /**
     * 当前线程参数列表(多Scope)
     */
    private Map<String, Map<String, Class>> variableTypeMap = new HashMap<>();

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    protected Map<String, Map<String, Object>> getVariableMap() {
        return variableMap;
    }

    protected Map<String, Map<String, Class>> getVariableTypeMap() {

        return variableTypeMap;
    }


    public Object retrieveVariable(String variableName) {
        return retrieveVariable(FlowConstant.SCOPE_DEFAULT, variableName);
    }

    public Object retrieveVariable(String scope, String variableName) {
        Map<String, Object> scopeVariableMap = variableMap.get(scope);
        Object variable = null;
        if (scopeVariableMap != null) {
            variable = scopeVariableMap.get(variableName);
        }
        return variable;
    }

    public Class retrieveVariableType(String variableName) {
        return retrieveVariableType(FlowConstant.SCOPE_DEFAULT, variableName);
    }

    public Class retrieveVariableType(String scope, String variableName) {
        Map<String, Class> scopeVariableTypeMap = variableTypeMap.get(scope);
        Class variableType = null;
        if (scopeVariableTypeMap != null) {
            variableType = scopeVariableTypeMap.get(variableName);
        }
        return variableType;
    }

    public void registerVariable(String scope, String variableName, Object variableValue, Class variableType) {
        //注册variable
        Map<String, Object> scopeVariableMap = variableMap.get(scope);
        if (scopeVariableMap == null) {
            scopeVariableMap = new HashMap<>();
            variableMap.put(scope, scopeVariableMap);
        }
        //注册variableType
        Map<String, Class> scopeVariableTypeMap = variableTypeMap.get(scope);
        if (scopeVariableTypeMap == null) {
            scopeVariableTypeMap = new HashMap<>();
            variableTypeMap.put(scope, scopeVariableTypeMap);
        }
        scopeVariableMap.put(variableName, variableValue);
        scopeVariableTypeMap.put(variableName, variableType);
    }

//    public void registerVariableList(Map<String, Object> variableMap, Map<String, Class> variableTypeMap) {
//        this.variableMap.putAll(variableMap);
//        this.variableTypeMap.putAll(variableTypeMap);
//    }

    //TODO:多Scope支持
    public void registerVariableList(Map<String, Map<String, Object>> variableMap, Map<String, Map<String, Class>> variableTypeMap) {
        this.variableMap.putAll(variableMap);
        this.variableTypeMap.putAll(variableTypeMap);
    }

    //TODO:多Scope支持
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

    //TODO:测试
    public void releaseVariable(Map<String, String> variables) {
        String scope;
        String variableName;
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            scope = variable.getKey();
            variableName = variable.getValue();
            Map<String, Object> scopeVariableMap = variableMap.get(scope);
            Map<String, Object> scopeVariableTypeMap = variableMap.get(scope);
            scopeVariableMap.remove(variableName);
            scopeVariableTypeMap.remove(variableName);
        }
    }


    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

}
