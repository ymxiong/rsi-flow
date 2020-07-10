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

    private FlowContext parentContext;
    /**
     * 当前线程流程名称
     */
    private String flowName;
    /**
     * 当前线程参数列表
     */
    private Map<String,Object> variableMap = new HashMap<>();
    /**
     * 当前线程参数类型
     */
    private Map<String,Class> variableTypeMap = new HashMap<>();

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, Object> variableMap) {
        this.variableMap = variableMap;
    }

    public Map<String, Class> getVariableTypeMap() {

        return variableTypeMap;
    }

    public void setVariableTypeMap(Map<String, Class> variableTypeMap) {
        this.variableTypeMap = variableTypeMap;
    }


    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public FlowContext getParentContext() {
        return parentContext;
    }

    public void setParentContext(FlowContext parentContext) {
        this.parentContext = parentContext;
    }
}
