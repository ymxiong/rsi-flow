package cc.eamon.open.flow.config.flow.node;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Author: lucas
 * Date: 2021/1/17
 * Email: lucas@eamon.cc
 */
public class FlowResultConfig implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8038221621970044928L;

    /**
     * 该结果的名称
     */
    @JSONField(ordinal = 1)
    private String name;

    /**
     * 该结果的标题
     */
    @JSONField(ordinal = 2)
    private String title;

    /**
     * 该结果的描述
     */
    @JSONField(ordinal = 3)
    private String description;

    /**
     * 该结果的类型
     */
    @JSONField(ordinal = 4)
    private String type;

    /**
     * 结果存放到的域
     */
    @JSONField(ordinal = 5)
    private String scope;

    /**
     * 解析目标
     */
    @JSONField(ordinal = 6)
    private String parse;

    /**
     * 是否可以为空
     */
    @JSONField(ordinal = 7)
    private Boolean notnull;

    /**
     * 为空时的默认值
     */
    @JSONField(ordinal = 8)
    private Object defaultValue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public Boolean getNotnull() {
        return notnull;
    }

    public void setNotnull(Boolean notnull) {
        this.notnull = notnull;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
