package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import org.springframework.util.StringUtils;

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

    public static String toJSONString(FlowRouteStrategyConfig config) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        if (!StringUtils.isEmpty(config.getSource()))
            builder.append("\"source\":").append("\"").append(config.getSource()).append("\", ");
        if (!StringUtils.isEmpty(config.getTarget()))
            builder.append("\"target\":").append("\"").append(config.getTarget()).append("\", ");
        if (!StringUtils.isEmpty(config.getStrategy()))
            builder.append("\"strategy\":").append("\"").append(config.getStrategy()).append("\", ");
        builder.deleteCharAt(builder.length() - 2);
        builder.append("}");
        return builder.toString();
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
