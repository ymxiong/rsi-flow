package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.route.structure.FlowNode;

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
    private String flowName;
    /**
     * 当前线程参数列表
     */
    private Map<String,Object> variableMap = new HashMap<>();
    /**
     * 当前线程参数类型
     */
    private Map<String,Class> variableTypeMap = new HashMap<>();


    private FlowNode startNode;
    
    private List<FlowNode> processNodes;//存储路由节点

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

    public List<FlowNode> getProcessNodes() {
		return processNodes;
	}

	public void setProcessNodes(List<FlowNode> processNodes) {
		this.processNodes = processNodes;
	}

    public FlowNode getStartNode() {
        return startNode;
    }

    public void setStartNode(FlowNode startNode) {
        this.startNode = startNode;
    }
}
