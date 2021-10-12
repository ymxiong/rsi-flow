package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

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
    private String title;

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
        flowConfig.setTitle(title);
        flowConfig.setParams(params);
        flowConfig.setResults(results);
        flowConfig.setNodes(nodes);
        flowConfig.setRoutes(routes);
        return flowConfig;
    }

    public String toJSONString() {
        FlowConfig flowConfig = build();
        Map<String, String> jsonMap = new LinkedHashMap<>();
        jsonMap.put("id", flowConfig.getId());
        jsonMap.put("type", flowConfig.getType());
        jsonMap.put("title", flowConfig.getTitle());
        List<String> params = new ArrayList<>();
        for (FlowParamConfig param : flowConfig.getParams()) {
            params.add(JSONObject.toJSONString(param));
        }
        jsonMap.put("params", JSONObject.toJSONString(params));
        List<String> results = new ArrayList<>();
        for (FlowResultConfig result : flowConfig.getResults()) {
            params.add(JSONObject.toJSONString(result));
        }
        jsonMap.put("results", JSONObject.toJSONString(results));
        List<String> nodes = new ArrayList<>();
        for (FlowNodeConfig node : flowConfig.getNodes()) {
            nodes.add(JSONObject.toJSONString(node));
        }
        jsonMap.put("nodes", JSONObject.toJSONString(nodes));
        List<String> routes = new ArrayList<>();
        for (FlowRouteConfig route : flowConfig.getRoutes()) {
            routes.add(JSONObject.toJSONString(route));
        }
        jsonMap.put("routes", JSONObject.toJSONString(routes));

        return jsonMap.toString();
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

    public FlowConfigBuilder addTitle(String title) {
        this.title = title;
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
