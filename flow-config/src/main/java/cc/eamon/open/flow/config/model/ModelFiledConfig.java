package cc.eamon.open.flow.config.model;

import java.io.Serializable;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 13:21
 */
public class ModelFiledConfig implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1770569928514391987L;

    /**
     * 模型属性名
     */
    private String name;

    /**
     * 模型属性类型
     */
    private String type;

    /**
     * 字段模型引用
     */
    private String reference;

    /**
     * 是否为空
     */
    private Boolean notnull;

    /**
     * 默认值
     */
    private Object defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
