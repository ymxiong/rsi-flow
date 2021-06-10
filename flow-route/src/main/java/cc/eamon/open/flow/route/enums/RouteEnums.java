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

    private String name;

    RouteEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取节点类型
     *
     * @param name 类型名称
     * @return 枚举
     */
    public static RouteEnums getConfigByName(String name) {
        for (RouteEnums value : RouteEnums.values()) {
            if (value.getName().equals(name)) return value;
        }
        return RouteEnums.DEFAULT;
    }

    /**
     * 确定名称是否被保留，用于id校验
     *
     * @param name 名称
     * @return 是否被保留
     */
    public static boolean isProtectName(String name) {
        for (RouteEnums value : RouteEnums.values()) {
            if (value.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * 打印保留字
     *
     * @return 保留字
     */
    public static String printProtectName() {
        StringBuilder names = new StringBuilder();
        for (RouteEnums value : RouteEnums.values()) {
            names.append(value.getName());
            names.append(", ");
        }
        names.deleteCharAt(names.length() - 1);
        names.deleteCharAt(names.length() - 1);
        return names.toString();
    }

}
