package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowResultConfigBuilder {

    /**
     * 该结果的名称
     */
    private String name;

    /**
     * 该结果的类型
     */
    private String type;

    /**
     * 结果存放到的域
     */
    private String scope;

    /**
     * 解析目标
     */
    private String parse;

    /**
     * 该结果是否可以为空
     */
    private Boolean notnull;

    /**
     * 为空时的默认值
     */
    private Object defaultValue;

    public FlowResultConfig build() {
        validate();
        FlowResultConfig flowResultConfig = new FlowResultConfig();
        flowResultConfig.setName(name);
        flowResultConfig.setType(type);
        flowResultConfig.setScope(scope);
        flowResultConfig.setParse(parse);
        flowResultConfig.setNotnull(notnull);
        flowResultConfig.setDefaultValue(defaultValue);
        return flowResultConfig;
    }

    public static String toJSONString(FlowResultConfig config) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        if (!StringUtils.isEmpty(config.getName()))
            builder.append("\"name\":").append("\"").append(config.getName()).append("\", ");
        if (!StringUtils.isEmpty(config.getType()))
            builder.append("\"type\":").append("\"").append(config.getType()).append("\", ");
        if (!StringUtils.isEmpty(config.getScope()))
            builder.append("\"scope\":").append("\"").append(config.getScope()).append("\", ");
        if (!StringUtils.isEmpty(config.getParse()))
            builder.append("\"parse\":").append("\"").append(config.getParse()).append("\", ");
        if (!StringUtils.isEmpty(config.getNotnull()))
            builder.append("\"notnull\":").append(config.getNotnull()).append(", ");
        if (!StringUtils.isEmpty(config.getDefaultValue()))
            builder.append("\"defaultValue\":").append(config.getDefaultValue()).append(", ");
        builder.deleteCharAt(builder.length() - 2);
        builder.append("}");
        return builder.toString();
    }

    private void validate() {
        Assert.notNull(name, "result name null");
        Assert.notNull(type, "result type null");
    }


    public FlowResultConfigBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FlowResultConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public FlowResultConfigBuilder addScope(String scope) {
        this.scope = scope;
        return this;
    }

    public FlowResultConfigBuilder addParse(String parse) {
        this.parse = parse;
        return this;
    }

    public FlowResultConfigBuilder addNotnull(Boolean notnull) {
        this.notnull = notnull;
        return this;
    }

    public FlowResultConfigBuilder addDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

}
