package cc.eamon.open.flow.route.enums;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:25:08
 */
public enum RouteStrategyEnums {


    /**
     * 开始节点
     */
    SIMPLE("simple"),
    ;

    private String name;

    RouteStrategyEnums(String name) {
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
    public static RouteStrategyEnums getStrategyByName(String name) {
        for (RouteStrategyEnums value : RouteStrategyEnums.values()) {
            if (value.getName().equals(name)) return value;
        }
        return RouteStrategyEnums.SIMPLE;
    }

}
