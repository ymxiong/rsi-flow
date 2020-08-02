package cc.eamon.open.flow.core.route.execute.strategy.enums;

import cc.eamon.open.flow.core.route.execute.strategy.*;

/**
 * 路由策略枚举
 * <p>
 * author: xsy
 */
public enum StrategyTypeEnum {

    START_EVENT("start", new EventStrategy()),
    EVENT("event", new EventStrategy()),//事件策略
    END_EVENT("end", new EndEventStrategy()),//结束策略
    GATEWAY("gateway", new GateWayStrategy()),//网关策略
    FLOW("flow", new FlowStrategy());//流程策略

    private String type;
    private RouteStrategy strategy;


    StrategyTypeEnum(String type, RouteStrategy strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    //获取对应枚举的策略
    public static RouteStrategy getStrategy(String type) {
        for (StrategyTypeEnum strategyTypeEnum : StrategyTypeEnum.values()) {
            if (strategyTypeEnum.type.equals(type)) {
                return strategyTypeEnum.strategy;
            }
        }
        return null;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public RouteStrategy getStrategy() {
        return strategy;
    }


    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }
}
