package cc.eamon.open.flow.config;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:57:55
 */
public class RouteConfig {
    //节点id，用于路由
    private String id;

    //节点类型
    private String type;

    //节点名，用于显示给用户查看
    private String name;

    //具体调用的FlowNode或Flow的Id
    private String reference;

    //目标节点
    private List<TargetConfig> targets;

    //默认节点
    private String defaultTarget;

    private List<FlowItemParam> params;

    private FlowItemResult result;


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

    public List<TargetConfig> getTargets() {
        return targets;
    }

    public void setTargets(List<TargetConfig> targets) {
        this.targets = targets;
    }

    public String getDefaultTarget() {
        return defaultTarget;
    }

    public void setDefaultTarget(String defaultTarget) {
        this.defaultTarget = defaultTarget;
    }

    public List<FlowItemParam> getParams() {
        return params;
    }

    public void setParams(List<FlowItemParam> params) {
        this.params = params;
    }

    public FlowItemResult getResult() {
        return result;
    }

    public void setResult(FlowItemResult result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
