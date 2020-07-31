package cc.eamon.open.flow.core.route.execute.strategy;

import cc.eamon.open.flow.core.route.structure.element.RouteElement;

import java.util.List;

public interface RouteStrategy {

    /**
     * 寻找下一节点（可能有多个）
     *
     * @param routeElement -- 当前节点
     * @return 下一节点的id
     */
    List<String> findWay(RouteElement routeElement);

}
