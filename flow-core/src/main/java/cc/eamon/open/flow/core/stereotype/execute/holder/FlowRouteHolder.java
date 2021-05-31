package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 21:46:19
 */
public class FlowRouteHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowRouteHolder.class);

    /**
     * 流程路由的配置
     */
    private FlowRouteConfig flowRouteConfig;

    public FlowRouteHolder(FlowRouteConfig flowRouteConfig) {
        Assert.notNull(flowRouteConfig, "流程路由配置不能为空");
        this.flowRouteConfig = flowRouteConfig;
    }

    public String getSource() {
        return flowRouteConfig.getSource();
    }

    public String getTarget() {
        return flowRouteConfig.getTarget();
    }

    public String getCondition() {
        return flowRouteConfig.getCondition();
    }

    public String getScope() {
        return flowRouteConfig.getScope();
    }

}
