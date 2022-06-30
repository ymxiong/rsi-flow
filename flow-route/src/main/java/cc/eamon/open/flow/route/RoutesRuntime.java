package cc.eamon.open.flow.route;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/6/30 1:21 下午
 **/
public class RoutesRuntime {

    private Routes routes;

    private RoutesRuntime parent;

    public RoutesRuntime(Routes routes) {
        this.routes = routes;
    }

    private RoutesRuntime() {
        throw new RuntimeException("路由运行时路由不能为空");
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public RoutesRuntime getParent() {
        return parent;
    }

    public void setParent(RoutesRuntime parent) {
        this.parent = parent;
    }
}
