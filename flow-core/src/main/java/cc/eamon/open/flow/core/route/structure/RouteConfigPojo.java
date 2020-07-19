package cc.eamon.open.flow.core.route.structure;

import cc.eamon.open.flow.core.route.Source;
import cc.eamon.open.flow.core.route.Target;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

/**
 * 用于封装RouteConfig底层数据结构
 *
 * @author xsy
 */
public class RouteConfigPojo implements Source, Target {

    private RouteConfig routeConfig;

    public RouteConfigPojo(RouteConfig routeConfig) {
        this.routeConfig = routeConfig;
    }

    @Override
    public String getName() {
        return routeConfig.getName();
    }

    @Override
    public String getId() {
        return routeConfig.getId();
    }


}
