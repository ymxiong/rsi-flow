package cc.eamon.open.flow.core.stereotype.config;

public class FlowItemParam {

    //该参数所在的域
    private String scope;

    //该参数的类型
    private String type;

    //该参数的名称
    private String name;

    //该参数是否非空
    private boolean notNull;

    //该参数的缺省值
    private String defaultValue;

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
