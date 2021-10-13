package cc.eamon.open.flow.config.flow;

import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:57:55
 */
public class FlowNodeConfig implements Serializable {

    private static final long serialVersionUID = -2464587723407410898L;

    /**
     * 节点id，用于路由
     */
    @JSONField(ordinal = 1)
    private String id;

    /**
     * 节点类型
     */
    @JSONField(ordinal = 2)
    private String type;

    /**
     * 节点名，用于显示给用户查看
     */
    @JSONField(ordinal = 3)
    private String title;

    /**
     * 是否是异步
     */
    @JSONField(ordinal = 4)
    private Boolean async;

    /**
     * 调度方法，仅在http等环境下调用
     */
    @JSONField(ordinal = 5)
    private String method;

    /**
     * 具体调用的FlowNode或Flow的Id
     */
    @JSONField(ordinal = 6)
    private String reference;

    /**
     * 流程参数
     */
    @JSONField(ordinal = 7)
    private List<FlowParamConfig> params = new ArrayList<>();

    /**
     * 流程结果
     */
    @JSONField(ordinal = 8)
    private List<FlowResultConfig> results = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<FlowParamConfig> getParams() {
        return params;
    }

    public void setParams(List<FlowParamConfig> params) {
        this.params = params;
    }

    public List<FlowResultConfig> getResults() {
        return results;
    }

    public void setResults(List<FlowResultConfig> results) {
        this.results = results;
    }

    public Boolean getAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }
}
