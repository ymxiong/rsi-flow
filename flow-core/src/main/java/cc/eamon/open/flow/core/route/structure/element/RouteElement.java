package cc.eamon.open.flow.core.route.structure.element;

import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;

import java.util.List;

/**
 * 用于封装RouteConfig底层数据结构
 *
 * @author xsy
 */
public class RouteElement implements Source, Target {

    private RouteConfig routeConfig;

    public RouteElement(RouteConfig routeConfig) {
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

    public List<TargetConfig> getTargetConfigs() {
        return routeConfig.getTargets();
    }

    public String getDefaultTarget() {
        return routeConfig.getDefaultTarget();
    }
}
