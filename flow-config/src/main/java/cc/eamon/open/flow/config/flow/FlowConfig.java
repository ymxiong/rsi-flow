package cc.eamon.open.flow.config.flow;

import cc.eamon.open.flow.config.CommonConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.annotation.JSONField;

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
    @JSONField(ordinal = 1)
    private String id;

    /**
     * 流程类型
     */
    @JSONField(ordinal = 2)
    private String type;

    /**
     * 流程名
     */
    @JSONField(ordinal = 3)
    private String title;

    /**
     * 流程参数
     */
    @JSONField(ordinal = 4)
    private List<FlowParamConfig> params = new ArrayList<>();

    /**
     * 流程结果
     */
    @JSONField(ordinal = 5)
    private List<FlowResultConfig> results = new ArrayList<>();

    /**
     * 节点列表
     */
    @JSONField(ordinal = 6)
    private List<FlowNodeConfig> nodes = new ArrayList<>();

    /**
     * 流程路由
     */
    @JSONField(ordinal = 7)
    private List<FlowRouteConfig> routes = new ArrayList<>();

    /**
     * 流程路由
     */
    @JSONField(ordinal = 8)
    private List<FlowRouteStrategyConfig> strategies = new ArrayList<>();

    /**
     * 任务调度配置
     */
    @JSONField(ordinal = 9)
    private FlowScheduleConfig schedule;

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

    public List<FlowRouteStrategyConfig> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<FlowRouteStrategyConfig> strategies) {
        this.strategies = strategies;
    }

    public FlowScheduleConfig getSchedule() {
        return schedule;
    }

    public void setSchedule(FlowScheduleConfig schedule) {
        this.schedule = schedule;
    }
}
