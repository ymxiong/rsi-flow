package cc.eamon.open.flow.core.route.structure;

import java.util.List;

public class FlowNode {
	
	//节点id
	private String nodeId;

    //节点类型
    private String type;

    //节点名
    private String name;

    //目标节点
    private List<FlowTarget> targets;

    //默认节点
    private String defaultTarget;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlowTarget> getTargets() {
        return targets;
    }

    public void setTargets(List<FlowTarget> targets) {
        this.targets = targets;
    }

    public String getDefaultTarget() {
        return defaultTarget;
    }

    public void setDefaultTarget(String defaultTarget) {
        this.defaultTarget = defaultTarget;
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}

