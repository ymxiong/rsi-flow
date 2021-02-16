package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

public interface ParseStrategy {
    void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, RouteConfig routeConfig);
}
