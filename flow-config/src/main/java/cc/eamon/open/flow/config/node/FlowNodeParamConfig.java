package cc.eamon.open.flow.config.node;

/**
 * Author: lucas
 * Date: 2021/1/17
 * Email: lucas@eamon.cc
 */
public class FlowNodeParamConfig {

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
    private String scope;

    /**
     * 该参数是否非空
     */
    private boolean notNull = true;

    /**
     * 该参数的缺省值
     */
    private String defaultValue = "";

    public FlowNodeParamConfig(String name, String type, String scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

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

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
