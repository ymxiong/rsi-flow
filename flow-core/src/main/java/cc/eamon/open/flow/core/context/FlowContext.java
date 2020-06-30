package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.element.FlowDetail;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.*;

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
    private FlowEngine flowName;
    /**
     * 当前线程参数列表
     */
    private Map<String,Object> variableMap = new HashMap<>();
    /**
     * 当前线程参数类型
     */
    private Map<String,Class> variableTypeMap = new HashMap<>();
    /**
     * 当前执行业务节点名称
     */
    private FlowDetail curNode;


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


    public FlowEngine getFlowName() {
        return flowName;
    }

    public void setFlowName(FlowEngine flowName) {
        this.flowName = flowName;
    }

    public FlowDetail getCurNode() {
        return curNode;
    }

    public void setCurNode(FlowDetail curNode) {
        this.curNode = curNode;
    }
}
