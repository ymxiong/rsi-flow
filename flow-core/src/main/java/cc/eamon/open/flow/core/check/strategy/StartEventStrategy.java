package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;
import cc.eamon.open.flow.core.check.structure.StartItem;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;

public class StartEventStrategy extends BaseParseStrategy {

    @Override
    public void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, RouteConfig routeConfig) {
        StartItem startItem = new StartItem(routeConfig);
        TargetConfig target = routeConfig.getTargets().get(0);
        RouteConfig nextNode = configContext.getRouteById(target.getTarget());
        ParseStrategy parseStrategy = ParseStrategyTypeEnum.getStrategy(nextNode.getType());
        parseStrategy.parse(configContext, checkResult, startItem, nextNode);
    }

}
