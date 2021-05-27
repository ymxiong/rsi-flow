//package cc.eamon.open.flow.core.check.strategy;
//
//
///**
// * 解析策略枚举
// * <p>
// * author: zjy
// */
//public enum ParseStrategyTypeEnums {
//
//    START_EVENT("start", new StartEventStrategy()),
//
//
//    END_EVENT("end", new EndEventStrategy()),//结束策略
//
//
//    INCLUSIVE_GATEWAY("inclusiveGateway", new InclusiveGateWayStrategy()),//多路网关策略
//
//
//    EXCLUSIVE_GATEWAY("exclusiveGateway", new ExclusiveGateWayStrategy()),//唯一网关策略
//
//
//    FLOW("flow", new FlowStrategy());//流程策略
//
//    private String type;
//    private ParseStrategy strategy;
//
//
//    ParseStrategyTypeEnums(String type, ParseStrategy strategy) {
//        this.type = type;
//        this.strategy = strategy;
//    }
//
//    //获取对应枚举的策略
//    public static ParseStrategy getStrategy(String type) {
//        for (ParseStrategyTypeEnums strategyTypeEnum : ParseStrategyTypeEnums.values()) {
//            if (strategyTypeEnum.type.equals(type)) {
//                return strategyTypeEnum.strategy;
//            }
//        }
//        return null;
//    }
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//
//    public ParseStrategy getStrategy() {
//        return strategy;
//    }
//
//
//    public void setStrategy(ParseStrategy strategy) {
//        this.strategy = strategy;
//    }
//}
