package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-09-13 16:35:54
 */
public class FlowConfigBuilder {

    private final FlowConfig flowConfig = new FlowConfig();

    public FlowConfigBuilder() {
    }

    public FlowConfig build() {
        validate();
        return flowConfig;
    }

    public static String toJSONString(FlowConfig config) {
        String TAB = FlowConstants.JSON_TAB;
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        if (!StringUtils.isEmpty(config.getId()))
            builder.append(TAB).append("\"id\":").append("\"").append(config.getId()).append("\",\n");
        if (!StringUtils.isEmpty(config.getName()))
            builder.append(TAB).append("\"name\":").append("\"").append(config.getName()).append("\",\n");
        if (!StringUtils.isEmpty(config.getType()))
            builder.append(TAB).append("\"type\":").append("\"").append(config.getType()).append("\",\n");
        if (!StringUtils.isEmpty(config.getTitle()))
            builder.append(TAB).append("\"title\":").append("\"").append(config.getTitle()).append("\",\n");
        if (!CollectionUtils.isEmpty(config.getParams())) {
            builder.append(TAB).append("\"params\":[\n");
            for (FlowParamConfig param : config.getParams()) {
                builder.append(TAB).append(TAB).append(FlowParamConfigBuilder.toJSONString(param)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append("],\n");
        }
        if (!CollectionUtils.isEmpty(config.getResults())) {
            builder.append(TAB).append("\"results\":[\n");
            for (FlowResultConfig result : config.getResults()) {
                builder.append(TAB).append(TAB).append(FlowResultConfigBuilder.toJSONString(result)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append("],\n");
        }

        if (!CollectionUtils.isEmpty(config.getNodes())) {
            builder.append(TAB).append("\"nodes\":[\n");
            for (FlowNodeConfig node : config.getNodes()) {
                builder.append(FlowNodeConfigBuilder.toJSONString(node)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append("],\n");
        }
        if (!CollectionUtils.isEmpty(config.getRoutes())) {
            builder.append(TAB).append("\"routes\":[\n");
            for (FlowRouteConfig route : config.getRoutes()) {
                builder.append(TAB).append(TAB).append(FlowRouteConfigBuilder.toJSONString(route)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append("],\n");
        }
        if (!CollectionUtils.isEmpty(config.getStrategies())) {
            builder.append(TAB).append("\"strategies\":[\n");
            for (FlowRouteStrategyConfig strategy : config.getStrategies()) {
                builder.append(TAB).append(TAB).append(FlowRouteStrategyConfigBuilder.toJSONString(strategy)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append("],\n");
        }
        builder.deleteCharAt(builder.length() - 2);
        builder.append("}");
        return builder.toString();
    }

    private void validate() {
        Assert.notNull(flowConfig.getId(), "flow config id null");
        Assert.notNull(flowConfig.getType(), "flow config type null");
    }

    public FlowConfigBuilder addConfig(FlowConfig config) {
        List<FlowParamConfig> params = new ArrayList<>();
        List<FlowResultConfig> results = new ArrayList<>();
        List<FlowNodeConfig> nodes = new ArrayList<>();
        List<FlowRouteConfig> routes = new ArrayList<>();

        if (config.getParams() != null) {
            for (FlowParamConfig param : config.getParams()) {
                params.add(new FlowParamConfigBuilder().addConfig(param).build());
            }
        }

        if (config.getResults() != null) {
            for (FlowResultConfig result : config.getResults()) {
                results.add(new FlowResultConfigBuilder().addConfig(result).build());
            }
        }

        if (config.getNodes() != null) {
            for (FlowNodeConfig node : config.getNodes()) {
                nodes.add(new FlowNodeConfigBuilder().addConfig(node).build());
            }
        }

        if (config.getRoutes() != null) {
            for (FlowRouteConfig route : config.getRoutes()) {
                routes.add(new FlowRouteConfigBuilder().addConfig(route).build());
            }
        }


        flowConfig.setId(config.getId());
        flowConfig.setName(config.getName());
        flowConfig.setType(config.getType());
        flowConfig.setTitle(config.getTitle());
        flowConfig.setDescription(config.getDescription());
        flowConfig.setParams(params);
        flowConfig.setResults(results);
        flowConfig.setNodes(nodes);
        flowConfig.setRoutes(routes);
        flowConfig.setStrategies(config.getStrategies());
        flowConfig.setSchedule(config.getSchedule());
        flowConfig.setModels(config.getModels());
        
        return this;
    }

    public FlowConfigBuilder addId(String id) {
        flowConfig.setId(id);
        return this;
    }

    public FlowConfigBuilder addName(String name) {
        flowConfig.setName(name);
        return this;
    }

    public FlowConfigBuilder addType(String type) {
        flowConfig.setType(type);
        return this;
    }

    public FlowConfigBuilder addTitle(String title) {
        flowConfig.setTitle(title);
        return this;
    }

    public FlowConfigBuilder addParams(FlowParamConfig... params) {
        flowConfig.getParams().addAll(Arrays.asList(params));
        return this;
    }

    public FlowConfigBuilder addResults(FlowResultConfig... results) {
        flowConfig.getResults().addAll(Arrays.asList(results));
        return this;
    }

    public FlowConfigBuilder addNodes(FlowNodeConfig... nodes) {
        flowConfig.getNodes().addAll(Arrays.asList(nodes));
        return this;
    }

    public FlowConfigBuilder addRoutes(FlowRouteConfig... routes) {
        flowConfig.getRoutes().addAll(Arrays.asList(routes));
        return this;
    }
}
