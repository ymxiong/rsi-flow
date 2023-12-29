package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowNodeConfigBuilder {

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
    private String title;

    /**
     * 是否是异步
     */
    private Boolean async;

    /**
     * 调度方法，仅在http等环境下调用
     */
    private String method;

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
    private List<FlowResultConfig> results = new ArrayList<>();

    public FlowNodeConfig build() {
        validate();
        FlowNodeConfig flowNodeConfig = new FlowNodeConfig();
        flowNodeConfig.setId(id);
        flowNodeConfig.setType(type);
        flowNodeConfig.setTitle(title);
        flowNodeConfig.setAsync(async);
        flowNodeConfig.setMethod(method);
        flowNodeConfig.setReference(reference);
        flowNodeConfig.setParams(params);
        flowNodeConfig.setResults(results);
        return flowNodeConfig;
    }

    public static String toJSONString(FlowNodeConfig config) {
        String TAB = FlowConstants.JSON_TAB;
        StringBuilder builder = new StringBuilder();
        builder.append(TAB).append(TAB).append("{\n");
        if (!StringUtils.isEmpty(config.getId()))
            builder.append(TAB).append(TAB).append(TAB).append("\"id\":").append("\"").append(config.getId()).append("\",\n");
        if (!StringUtils.isEmpty(config.getType()))
            builder.append(TAB).append(TAB).append(TAB).append("\"type\":").append("\"").append(config.getType()).append("\",\n");
        if (!StringUtils.isEmpty(config.getTitle()))
            builder.append(TAB).append(TAB).append(TAB).append("\"title\":").append("\"").append(config.getTitle()).append("\",\n");
        if (!StringUtils.isEmpty(config.getAsync()))
            builder.append(TAB).append(TAB).append(TAB).append("\"async\":").append("\"").append(config.getAsync()).append("\",\n");
        if (!StringUtils.isEmpty(config.getReference()))
            builder.append(TAB).append(TAB).append(TAB).append("\"reference\":").append("\"").append(config.getReference()).append("\",\n");
        if (!CollectionUtils.isEmpty(config.getParams())) {
            builder.append(TAB).append(TAB).append(TAB).append("\"params\":[\n");
            for (FlowParamConfig param : config.getParams()) {
                builder.append(TAB).append(TAB).append(TAB).append(TAB).append(FlowParamConfigBuilder.toJSONString(param)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append(TAB).append(TAB).append("],\n");
        }
        if (!CollectionUtils.isEmpty(config.getResults())) {
            builder.append(TAB).append(TAB).append(TAB).append("\"results\":[\n");
            for (FlowResultConfig result : config.getResults()) {
                builder.append(TAB).append(TAB).append(TAB).append(TAB).append(FlowResultConfigBuilder.toJSONString(result)).append(",\n");
            }
            builder.deleteCharAt(builder.length() - 2);
            builder.append(TAB).append(TAB).append(TAB).append("],\n");
        }
        builder.deleteCharAt(builder.length() - 2);
        builder.append(TAB).append(TAB).append("}");
        return builder.toString();
    }

    private void validate() {
        Assert.notNull(id, "node name null");
        Assert.notNull(type, "node type null");
    }


    public FlowNodeConfigBuilder addConfig(FlowNodeConfig config) {
        List<FlowParamConfig> params = new ArrayList<>();
        List<FlowResultConfig> results = new ArrayList<>();

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

        this.params = params;
        this.results = results;

        return this
                .addId(config.getId())
                .addType(config.getType())
                .addTitle(config.getTitle())
                .addAsync(config.getAsync())
                .addMethod(config.getMethod())
                .addReference(config.getReference())
                ;
    }

    public FlowNodeConfigBuilder addId(String id) {
        this.id = id;
        return this;
    }

    public FlowNodeConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public FlowNodeConfigBuilder addTitle(String title) {
        this.title = title;
        return this;

    }

    public FlowNodeConfigBuilder addAsync(Boolean async) {
        this.async = async;
        return this;
    }

    public FlowNodeConfigBuilder addMethod(String method) {
        this.method = method;
        return this;
    }

    public FlowNodeConfigBuilder addReference(String reference) {
        this.reference = reference;
        return this;
    }

    public FlowNodeConfigBuilder addParams(FlowParamConfig... params) {
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    public FlowNodeConfigBuilder addResults(FlowResultConfig... results) {
        this.results.addAll(Arrays.asList(results));
        return this;
    }
}
