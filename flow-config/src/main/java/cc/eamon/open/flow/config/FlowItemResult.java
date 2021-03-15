package cc.eamon.open.flow.config;

public class FlowItemResult {

    //结果存放到的域
    private String scope;

    //该结果的类型
    private String type;

    //该结果的名称
    private String name;

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
