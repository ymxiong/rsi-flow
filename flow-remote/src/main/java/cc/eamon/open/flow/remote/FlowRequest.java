package cc.eamon.open.flow.remote;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 20:14:43
 */
public class FlowRequest {

    /**
     * 流程ID
     */
    private String flowId;

    /**
     * 返回结果集合
     */
    private Map<String, String> results;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
