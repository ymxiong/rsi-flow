package cc.eamon.open.flow.core.route.execute.strategy;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.route.execute.strategy.enums.StrategyTypeEnum;
import cc.eamon.open.flow.core.route.structure.RouteModel;
import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.config.TargetConfig;
import cc.eamon.open.flow.core.util.JexlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExclusiveGateWayStrategy implements RouteStrategy {

    @Override
    public List<String> findWay(RouteElement routeElement) {
        List<String> results = new ArrayList<>();
        //目标
        List<TargetConfig> targets = routeElement.getTargetConfigs();

//        Map<String, Object> env = getEnv();
        //挑选符合条件的出口
        for (TargetConfig targetConfig : targets) {
            String condition = targetConfig.getCondition();//条件
            String target = targetConfig.getTarget();//出口id
            Map<String, Object> env = getEnv(targetConfig.getScope());
            if (judgeCondition(condition, env)) {
                results.add(target);
            }
        }
        return results;
    }

    @Override
    public void processTarget(RouteElement target, List<Target> targets, RouteModel routeModel) {
        if (target.getTargetConfigs().size() != 1) {
            //发散网关
            RouteStrategy strategy = StrategyTypeEnum.getStrategy(target.getType());
            if (strategy == null)
                throw new RuntimeException("route strategy can't be null");
            List<String> nextIds = strategy.findWay(target);
            if (nextIds != null) {
                for (String nextId : nextIds) {
                    RouteElement targetElement = routeModel.getElementById(nextId);
                    RouteStrategy targetStrategy = StrategyTypeEnum.getStrategy(targetElement.getType());
                    if (targetStrategy == null)
                        throw new RuntimeException("route strategy can't be null");
                    targetStrategy.processTarget(targetElement, targets, routeModel);
                }
            }
        }
    }


    /**
     * 获取当前环境
     *
     * @author xsy
     */
    private Map<String, Object> getEnv(String scope) {
        return new FlowContextExtractor().getVariableMap().get(scope);
    }

    /**
     * 判断条件是否正确
     *
     * @param condition -- 条件
     * @author xsy
     */
    private boolean judgeCondition(String condition, Map<String, Object> env) {
        //这里需要对表达式进行判断，先简单判断一下
        return (boolean) (Boolean) JexlUtils.invokeMethod(condition, env);
    }
}
