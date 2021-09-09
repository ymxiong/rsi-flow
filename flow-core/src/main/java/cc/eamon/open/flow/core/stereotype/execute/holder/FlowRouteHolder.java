package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.route.stereotype.Condition;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 21:46:19
 */
public class FlowRouteHolder implements Route {

    private static Logger logger = LoggerFactory.getLogger(FlowRouteHolder.class);

    /**
     * 流程路由的配置
     */
    private FlowRouteConfig flowRouteConfig;

    /**
     * 流程路由源头
     */
    private Source source;

    /**
     * 流程路由目标
     */
    private Target target;

    /**
     * 流程路由条件
     */
    private Condition condition;

    public FlowRouteHolder(FlowRouteConfig flowRouteConfig, Source source, Target target, Condition condition) {
        Assert.notNull(flowRouteConfig, "流程路由配置不能为空");
        this.flowRouteConfig = flowRouteConfig;
        this.source = source;
        this.target = target;
        this.condition = condition;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Override
    public Condition getCondition() {
        return condition;
    }


    public String getScope() {
        return flowRouteConfig.getScope();
    }

}
