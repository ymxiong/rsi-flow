package cc.eamon.open.flow.route.enums;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-24 17:31:41
 */
public enum ConditionEnums {

    /**
     * 绝对执行条件
     */
    TRUE("true"),

    /**
     * 绝对不执行条件
     */
    FALSE("false"),

    /**
     * 默认条件
     */
    DEFAULT("default"),
    ;

    private String id;

    ConditionEnums(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * 获取节点类型
     *
     * @param id 类型名称
     * @return 枚举
     */
    public static ConditionEnums getConfigById(String id) {
        for (ConditionEnums value : ConditionEnums.values()) {
            if (value.getId().equals(id)) return value;
        }
        return ConditionEnums.FALSE;
    }

}
