package cc.eamon.open.flow.core.route.structure;

import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.config.RouteConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由业务模型
 *
 * @author : xsy
 */
public class RouteModel {

    // 路由节点列表
    private List<RouteElement> elements;

    public RouteModel(List<RouteConfig> routeConfigs) {
        elements = new ArrayList<>();
        for (RouteConfig routeConfig : routeConfigs) {
            elements.add(new RouteElement(routeConfig));
        }
    }

    /**
     * 获取第一个执行的节点【开始节点的下一个节点】
     *
     * @author xsy
     */
    public RouteElement getStartNode() {
        RouteElement start = null;
        for (RouteElement element : elements) {
            if (element.getType().equals("start")) {
                start = element;
                break;
            }
        }
        String target = start.getTargetConfigs().get(0).getTarget();
        return getElementById(target);
    }

    /**
     * 根据id获取路由element
     *
     * @param id -- 节点的id
     * @author xsy
     */
    public RouteElement getElementById(String id) {
        for (RouteElement element : elements) {
            if (element.getId().equals(id))
                return element;
        }
        return null;
    }

}
