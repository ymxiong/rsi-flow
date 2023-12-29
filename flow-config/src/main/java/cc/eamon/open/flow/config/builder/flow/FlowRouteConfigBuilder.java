package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import org.springframework.util.StringUtils;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowRouteConfigBuilder {

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
    private String condition = "true";

    /**
     * 目标条件所处域
     */
    private String scope;

    public FlowRouteConfig build() {
        validate();
        FlowRouteConfig flowRouteConfig = new FlowRouteConfig();
        flowRouteConfig.setSource(source);
        flowRouteConfig.setTarget(target);
        flowRouteConfig.setCondition(condition);
        flowRouteConfig.setScope(scope);
        return flowRouteConfig;
    }

    public static String toJSONString(FlowRouteConfig config) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        if (!StringUtils.isEmpty(config.getSource()))
            builder.append("\"source\":").append("\"").append(config.getSource()).append("\", ");
        if (!StringUtils.isEmpty(config.getTarget()))
            builder.append("\"target\":").append("\"").append(config.getTarget()).append("\", ");
        if (!StringUtils.isEmpty(config.getCondition()))
            builder.append("\"condition\":").append("\"").append(config.getCondition()).append("\", ");
        if (!StringUtils.isEmpty(config.getScope()))
            builder.append("\"scope\":").append("\"").append(config.getScope()).append("\", ");
        builder.deleteCharAt(builder.length() - 2);
        builder.append("}");
        return builder.toString();
    }

    private void validate() {

    }


    public FlowRouteConfigBuilder addConfig(FlowRouteConfig config) {
        return this
                .addSource(config.getSource())
                .addTarget(config.getTarget())
                .addCondition(config.getCondition())
                .addScope(config.getScope())
                ;
    }

    public FlowRouteConfigBuilder addSource(String source) {
        this.source = source;
        return this;
    }

    public FlowRouteConfigBuilder addTarget(String target) {
        this.target = target;
        return this;
    }

    public FlowRouteConfigBuilder addCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public FlowRouteConfigBuilder addScope(String scope) {
        this.scope = scope;
        return this;
    }
}
