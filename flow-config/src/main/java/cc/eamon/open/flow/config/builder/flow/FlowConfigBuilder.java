package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-09-13 16:35:54
 */
public class FlowConfigBuilder {

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

    public FlowConfigBuilder() {
    }

    public FlowConfig build() {
        validate();
        FlowConfig flowConfig = new FlowConfig();
        flowConfig.setId(id);
        flowConfig.setType(type);
        flowConfig.setName(name);
        flowConfig.setParams(params);
        flowConfig.setResults(results);
        flowConfig.setNodes(nodes);
        flowConfig.setRoutes(routes);
        return flowConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }

    public FlowConfigBuilder addId(String id) {
        this.id = id;
        return this;
    }

    public FlowConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public FlowConfigBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FlowConfigBuilder addParams(FlowParamConfig... params) {
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    public FlowConfigBuilder addResults(FlowResultConfig... results) {
        this.results.addAll(Arrays.asList(results));
        return this;
    }

    public FlowConfigBuilder addNodes(FlowNodeConfig... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

    public FlowConfigBuilder addRoutes(FlowRouteConfig... routes) {
        this.routes.addAll(Arrays.asList(routes));
        return this;
    }
}
