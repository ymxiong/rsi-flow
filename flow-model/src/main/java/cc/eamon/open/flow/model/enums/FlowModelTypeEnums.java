package cc.eamon.open.flow.model.enums;

public enum FlowModelTypeEnums {

    FLOW_REFERENCE_MODEL("FLOW_REFERENCE_MODEL", "引用模型"),
    FLOW_USER_DEFINE_MODEL("FLOW_USER_DEFINE_MODEL", "用户定义模型"),
    FLOW_PARAMETERIZE_MODEL("FLOW_PARAMETERIZE_MODEL", "用户复合参数模型");

    private String type;
    private String info;

    FlowModelTypeEnums(String type, String info) {
        this.type = type;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}

