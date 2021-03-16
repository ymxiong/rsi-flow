package cc.eamon.open.flow.core.stereotype;

import java.util.Map;

public class FlowEntranceParam {
    String flowId;

    String resultName;

    String resultScope;

    Map<String, Object> params;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getResultScope() {
        return resultScope;
    }

    public void setResultScope(String resultScope) {
        this.resultScope = resultScope;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
