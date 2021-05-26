package cc.eamon.open.flow.config.flow.node;

import cc.eamon.open.flow.config.constants.FlowConstants;

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
    private String name;

    /**
     * 该参数的类型
     */
    private String type;

    /**
     * 该参数所在的域
     */
    private String scope = FlowConstants.SCOPE_DEFAULT;

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
}
