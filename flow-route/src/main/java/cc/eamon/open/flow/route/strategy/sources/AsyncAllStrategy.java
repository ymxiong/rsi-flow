package cc.eamon.open.flow.route.strategy.sources;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.AsyncAllResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.SourceRouteStrategy;
import cc.eamon.open.flow.route.stereotype.Target;
import cc.eamon.open.flow.route.util.ConditionHelper;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class AsyncAllStrategy implements SourceRouteStrategy {
    private Source source;

    private List<Route> routes;

    public AsyncAllStrategy(Source source) {
        this.source = source;
        routes=new ArrayList<>();
    }

    public AsyncAllStrategy(Route route) {
        this.source = route.getSource();
        routes = new ArrayList<>();
        routes.add(route);
    }

    @Override
    public String getSRType() {
        return SourceRouteStrategyEnums.ASYNCALLSTRATEGY.getType();
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public RouteResult route(Source source, Map<String, Object> variables) {
        AsyncAllResult asyncAllResult = null;
        List<Target> targets = ConditionHelper.JexlEngine(source, routes, variables);
        Assert.isTrue(targets.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());
        asyncAllResult = new AsyncAllResult(targets);
        return asyncAllResult;
    }


    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }

}
