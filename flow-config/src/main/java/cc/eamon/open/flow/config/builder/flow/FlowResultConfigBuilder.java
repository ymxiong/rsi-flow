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

    private final FlowResultConfig config = new FlowResultConfig();

    public FlowResultConfig build() {
        validate();
        return config;
    }

    public static String toJSONString(FlowResultConfig config) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        if (!StringUtils.isEmpty(config.getTitle()))
            builder.append("\"title\":").append("\"").append(config.getTitle()).append("\", ");
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
        Assert.notNull(config.getName(), "param name null");
        Assert.notNull(config.getType(), "param type null");
    }


    public FlowResultConfigBuilder addConfig(FlowResultConfig config) {
        return this
                .addTitle(config.getTitle())
                .addName(config.getName())
                .addType(config.getType())
                .addScope(config.getScope())
                .addParse(config.getParse())
                .addNotnull(config.getNotnull())
                .addDefaultValue(config.getDefaultValue());
    }

    public FlowResultConfigBuilder addTitle(String title) {
        config.setTitle(title);
        return this;
    }

    public FlowResultConfigBuilder addName(String name) {
        config.setName(name);
        return this;
    }

    public FlowResultConfigBuilder addType(String type) {
        config.setType(type);
        return this;
    }

    public FlowResultConfigBuilder addScope(String scope) {
        config.setScope(scope);
        return this;
    }

    public FlowResultConfigBuilder addParse(String parse) {
        config.setParse(parse);
        return this;
    }

    public FlowResultConfigBuilder addNotnull(Boolean notnull) {
        config.setNotnull(notnull);
        return this;
    }

    public FlowResultConfigBuilder addDefaultValue(Object defaultValue) {
        config.setDefaultValue(defaultValue);
        return this;
    }

}
