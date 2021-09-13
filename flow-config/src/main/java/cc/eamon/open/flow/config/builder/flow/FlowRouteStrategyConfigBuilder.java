package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowRouteStrategyConfigBuilder {

    /**
     * 源节点
     */
    private String source;

    /**
     * 目标节点
     */
    private String target;

    /**
     * 到目标节点的条件
     */
    private String strategy = "normal";

    public FlowRouteStrategyConfig build() {
        validate();
        FlowRouteStrategyConfig flowRouteStrategyConfig = new FlowRouteStrategyConfig();
        flowRouteStrategyConfig.setSource(source);
        flowRouteStrategyConfig.setTarget(target);
        flowRouteStrategyConfig.setStrategy(strategy);
        return flowRouteStrategyConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }


    public FlowRouteStrategyConfigBuilder addSource(String source) {
        this.source = source;
        return this;
    }

    public FlowRouteStrategyConfigBuilder addTarget(String target) {
        this.target = target;
        return this;
    }

    public FlowRouteStrategyConfigBuilder addStrategy(String strategy) {
        this.strategy = strategy;
        return this;
    }
}
