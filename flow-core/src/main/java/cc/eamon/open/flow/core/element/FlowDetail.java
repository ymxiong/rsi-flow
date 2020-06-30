package cc.eamon.open.flow.core.element;

/**
 * @author: lucas
 * @date: 2020/6/28
 * @email: lucas@eamon.cc
 */
public class FlowDetail {
	private String curNodeId=null;
    private String curNodeName=null;
    
    private FlowDetail nextNode=null;

    public String getCurNodeName() {
        return curNodeName;
    }

    public void setCurNodeName(String curNodeName) {
        this.curNodeName = curNodeName;
    }


    public FlowDetail getNextNode() {
        return nextNode;
    }

    public void setNextNode(FlowDetail nextNode) {
        this.nextNode = nextNode;
    }

    public FlowDetail(String curNodeName) {
        this.curNodeName = curNodeName;
    }

	public String getCurNodeId() {
		return curNodeId;
	}

	public void setCurNodeId(String curNodeId) {
		this.curNodeId = curNodeId;
	}

	public FlowDetail(String curNodeId, String curNodeName) {
		this.curNodeId = curNodeId;
		this.curNodeName = curNodeName;
	}
	
	
}
