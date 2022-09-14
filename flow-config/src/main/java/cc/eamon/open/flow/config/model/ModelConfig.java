package cc.eamon.open.flow.config.model;

import cc.eamon.open.flow.config.CommonConfig;

import java.io.Serializable;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-20 20:47:56
 */
public class ModelConfig implements CommonConfig, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5032520065442675208L;

    /**
     * 模型id
     */
    private String id;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 模型参考
     */
    private String reference;

    /**
     * 模型目标引用
     */
    private String targetReference;

    private List<ModelFiledConfig> fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTargetReference() {
        return targetReference;
    }

    public void setTargetReference(String targetReference) {
        this.targetReference = targetReference;
    }

    public List<ModelFiledConfig> getFields() {
        return fields;
    }

    public void setFields(List<ModelFiledConfig> fields) {
        this.fields = fields;
    }
}
