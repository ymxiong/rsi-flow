package cc.eamon.open.flow.config.flow;

import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;

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
    private String id;

    /**
     * 节点类型
     */
    private String type;

    /**
     * 节点名，用于显示给用户查看
     */
    private String name;

    /**
     * 具体调用的FlowNode或Flow的Id
     */
    private String reference;

    /**
     * 流程参数
     */
    private List<FlowParamConfig> params = new ArrayList<>();

    /**
     * 流程结果
     */
    private FlowResultConfig result;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public FlowResultConfig getResult() {
        return result;
    }

    public void setResult(FlowResultConfig result) {
        this.result = result;
    }
}
