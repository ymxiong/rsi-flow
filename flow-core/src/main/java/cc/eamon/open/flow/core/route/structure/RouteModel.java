package cc.eamon.open.flow.core.route.structure;

import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由业务模型
 * @author     : xsy
 */
public class RouteModel {

    // 路由节点列表
    private List<RouteElement> elements;

    public RouteModel(List<RouteConfig> routeConfigs){
        elements = new ArrayList<>();
        for (RouteConfig routeConfig : routeConfigs) {
            //name, id, type
            elements.add(new RouteElement(routeConfig.getName(),routeConfig.getId(), routeConfig.getType()));
        }
    }

    /**
     * 获取开始节点
     *
     * @author  xsy
     */
    public RouteElement getStartNode(){
        for (RouteElement element : elements) {
            if(element.getType().equals("start"))
                return element;
        }
        return null;
    }
}
