package cc.eamon.open.flow.core.route.structure.element;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;

import java.util.List;

/**
 * 用于封装RouteConfig底层数据结构
 *
 * @author xsy
 */
public class RouteElement implements Source, Target {

    private FlowNodeConfig routeConfig;

    public RouteElement(FlowNodeConfig routeConfig) {
        this.routeConfig = routeConfig;
    }

    @Override
    public String getId() {
        return routeConfig.getId();
    }

    @Override
    public String getName() {
        return routeConfig.getName();
    }

    @Override
    public String getType() {
        return routeConfig.getType();
    }

    @Override
    public String getReference() {
        return routeConfig.getReference();
    }

    public List<FlowRouteConfig> getTargetConfigs() {
        return null;
//        return routeConfig.getTargets();
    }

    public String getDefaultTarget() {
        return null;
//        return routeConfig.getDefaultTarget();
    }
}
