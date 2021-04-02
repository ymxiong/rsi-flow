package cc.eamon.open.flow.config;

import cc.eamon.open.flow.config.enums.FlowTypeEnums;
import cc.eamon.open.flow.config.node.FlowNodeParamConfig;
import cc.eamon.open.flow.config.node.FlowNodeResultConfig;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:57:55
 */
public class RouteConfig {

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
     * 目标节点
     */
    private List<TargetConfig> targets;

    /**
     * 默认节点
     */
    private String defaultTarget;

    /**
     * 流程节点参数
     */
    private List<FlowNodeParamConfig> params;

    /**
     * 流程节点返回值
     */
    private FlowNodeResultConfig result;

    public RouteConfig(String id, FlowTypeEnums type) {
        this.id = id;
        this.type = type.getName();
    }

    public RouteConfig(String id, FlowTypeEnums type, List<TargetConfig> targets) {
        this.id = id;
        this.type = type.getName();
        this.targets = targets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(FlowTypeEnums type) {
        this.type = type.getName();
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

    public List<FlowNodeParamConfig> getParams() {
        return params;
    }

    public void setParams(List<FlowNodeParamConfig> params) {
        this.params = params;
    }

    public FlowNodeResultConfig getResult() {
        return result;
    }

    public void setResult(FlowNodeResultConfig result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
