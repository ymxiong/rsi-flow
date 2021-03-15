package cc.eamon.open.flow.remote;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 20:14:27
 */
public class FlowRequest implements Serializable {

    private String flowId;

    private Map<String, Object> params;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
