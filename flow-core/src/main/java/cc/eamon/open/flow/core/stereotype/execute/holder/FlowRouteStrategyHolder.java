package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.RouteStrategy;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 21:46:19
 */
public class FlowRouteStrategyHolder implements RouteStrategy {

    private static Logger logger = LoggerFactory.getLogger(FlowRouteStrategyHolder.class);

    /**
     * 流程路由的配置
     */
    private FlowRouteStrategyConfig flowRouteStrategyConfig;

    /**
     *
     */
    private Source source;

    /**
     * 流程路由条件
     */
    private RouteStrategy strategy;

    public FlowRouteStrategyHolder(FlowRouteStrategyConfig flowRouteStrategyConfig, Source source, RouteStrategy strategy) {
        Assert.notNull(flowRouteStrategyConfig, "流程路由策略配置不能为空");
        this.flowRouteStrategyConfig = flowRouteStrategyConfig;
        this.source = source;
        this.strategy = strategy;
    }


    public FlowRouteStrategyConfig getFlowRouteStrategyConfig() {
        return flowRouteStrategyConfig;
    }

    public void setFlowRouteStrategyConfig(FlowRouteStrategyConfig flowRouteStrategyConfig) {
        this.flowRouteStrategyConfig = flowRouteStrategyConfig;
    }

    @Override
    public String getType() {
        return strategy.getType();
    }

    public Source getSource() {
        return source;
    }

    @Override
    public RouteResult route(Source source, Map<String, Object> variables) {
        return strategy.route(source, variables);
    }

    @Override
    public void addRoute(Route route) {
        strategy.addRoute(route);
    }

    @Override
    public void setEngine(VelocityEngine engine) {

    }

    public void setSource(Source source) {
        this.source = source;
    }

    public RouteStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }
}
