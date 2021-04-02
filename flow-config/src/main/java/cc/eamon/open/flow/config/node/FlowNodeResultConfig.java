package cc.eamon.open.flow.config.node;

/**
 * Author: lucas
 * Date: 2021/1/17
 * Email: lucas@eamon.cc
 */
public class FlowNodeResultConfig {

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

    public FlowNodeResultConfig(String name, String type, String scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
