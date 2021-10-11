package cc.eamon.open.flow.config.flow.node;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Author: lucas
 * Date: 2021/1/17
 * Email: lucas@eamon.cc
 */
public class FlowParamConfig implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7679289072632020689L;

    /**
     * 该参数的名称
     */
    @JSONField(ordinal = 1)
    private String name;

    /**
     * 该参数的类型
     */
    @JSONField(ordinal = 2)
    private String type;

    /**
     * 该参数所在的域
     */
    @JSONField(ordinal = 3)
    private String scope;

    /**
     * 是否可以为空
     */
    @JSONField(ordinal = 4)
    private String notnull = "true";

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getNotnull() {
        return Boolean.parseBoolean(notnull);
    }

    public void setNotnull(String notnull) {
        this.notnull = notnull;
    }
}
