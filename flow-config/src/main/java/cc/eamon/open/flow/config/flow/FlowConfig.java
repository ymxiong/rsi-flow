package cc.eamon.open.flow.config.flow;

import cc.eamon.open.flow.config.CommonConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:56:34
 */
public class FlowConfig implements CommonConfig, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4118193060288481678L;

    /**
     * 流程id
     */
    private String id;

    /**
     * 流程类型
     */
    private String type;

    /**
     * 流程名
     */
    private String name;

    /**
     * 流程参数
     */
    private List<FlowParamConfig> params = new ArrayList<>();

    /**
     * 流程结果
     */
    private List<FlowResultConfig> results = new ArrayList<>();

    /**
     * 节点列表
     */
    private List<FlowNodeConfig> nodes = new ArrayList<>();


    /**
     * 流程路由
     */
    private List<FlowRouteConfig> routes = new ArrayList<>();

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

    public List<FlowNodeConfig> getNodes() {
        return nodes;
    }

    public void setNodes(List<FlowNodeConfig> nodes) {
        this.nodes = nodes;
    }

    public List<FlowRouteConfig> getRoutes() {
        return routes;
    }

    public void setRoutes(List<FlowRouteConfig> routes) {
        this.routes = routes;
    }
}
