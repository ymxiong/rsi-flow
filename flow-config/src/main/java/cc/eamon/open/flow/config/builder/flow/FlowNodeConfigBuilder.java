package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

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

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

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
