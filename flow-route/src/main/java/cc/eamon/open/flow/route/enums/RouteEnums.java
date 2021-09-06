package cc.eamon.open.flow.route.enums;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-24 17:31:41
 */
public enum RouteEnums {

    /**
     * 开始节点
     */
    START("start"),

    /**
     * 结束节点
     */
    END("end"),

    /**
     * 默认节点
     */
    DEFAULT("default"),
    ;

    private String id;

    RouteEnums(String id) {
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
    public static RouteEnums getConfigById(String id) {
        for (RouteEnums value : RouteEnums.values()) {
            if (value.getId().equals(id)) return value;
        }
        return RouteEnums.DEFAULT;
    }

    /**
     * 确定名称是否被保留，用于id校验
     *
     * @param id 名称
     * @return 是否被保留
     */
    public static boolean isProtectId(String id) {
        for (RouteEnums value : RouteEnums.values()) {
            if (value.getId().equals(id)) return true;
        }
        return false;
    }

    /**
     * 打印保留字
     *
     * @return 保留字
     */
    public static String printProtectId() {
        StringBuilder names = new StringBuilder();
        for (RouteEnums value : RouteEnums.values()) {
            names.append(value.getId());
            names.append(", ");
        }
        names.deleteCharAt(names.length() - 1);
        names.deleteCharAt(names.length() - 1);
        return names.toString();
    }

}
