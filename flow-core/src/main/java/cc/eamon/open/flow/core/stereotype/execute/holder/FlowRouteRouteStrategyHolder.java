package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 21:46:19
 */
public class FlowRouteRouteStrategyHolder implements SourceRouteStrategy, TargetRouteStrategy {

    private static Logger logger = LoggerFactory.getLogger(FlowRouteRouteStrategyHolder.class);

    /**
     * 流程路由的配置
     */
    private FlowRouteStrategyConfig flowRouteStrategyConfig;

    /**
     *
     */
    private Source source;


    /**
     *
     */
    private Target target;

    /**
     * 流程路由条件
     */
    private SourceRouteStrategy strategy;

    /**
     *
     */
    private TargetRouteStrategy targetRouteStrategy;

    public FlowRouteRouteStrategyHolder(FlowRouteStrategyConfig flowRouteStrategyConfig, Source source, SourceRouteStrategy strategy) {
        Assert.notNull(flowRouteStrategyConfig, "流程路由策略配置不能为空");
        this.flowRouteStrategyConfig = flowRouteStrategyConfig;
        this.source = source;
        this.strategy = strategy;
    }


    public FlowRouteRouteStrategyHolder(FlowRouteStrategyConfig flowRouteStrategyConfig, Target target, TargetRouteStrategy targetRouteStrategy) {
        this.flowRouteStrategyConfig = flowRouteStrategyConfig;
        this.target = target;
        this.targetRouteStrategy = targetRouteStrategy;
    }

    public FlowRouteStrategyConfig getFlowRouteStrategyConfig() {
        return flowRouteStrategyConfig;
    }

    public void setFlowRouteStrategyConfig(FlowRouteStrategyConfig flowRouteStrategyConfig) {
        this.flowRouteStrategyConfig = flowRouteStrategyConfig;
    }

    @Override
    public String getTRType() {
        return targetRouteStrategy.getTRType();
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Override
    public RouteResult route(RouteResult routeResult) {
        return targetRouteStrategy.route(routeResult);
    }

    @Override
    public String getSRType() {
        return strategy.getSRType();
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

    public void setSource(Source source) {
        this.source = source;
    }

    public SourceRouteStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SourceRouteStrategy strategy) {
        this.strategy = strategy;
    }
}
