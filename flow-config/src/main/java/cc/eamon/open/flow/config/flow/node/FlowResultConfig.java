package cc.eamon.open.flow.config.flow.node;

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

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }
}
