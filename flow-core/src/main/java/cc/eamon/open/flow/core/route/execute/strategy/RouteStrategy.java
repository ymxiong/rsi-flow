package cc.eamon.open.flow.core.route.execute.strategy;

import cc.eamon.open.flow.core.route.structure.RouteModel;
import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.route.structure.element.Target;

import java.util.List;

public interface RouteStrategy {

    /**
     * 寻找下一节点（可能有多个）
     *
     * @param routeElement -- 当前节点
     * @return 下一节点的id
     */
    List<String> findWay(RouteElement routeElement);


    /**
     * 处理目标节点
     *
     * @author  xsy
     * @param  target -- 待处理目标
     * @param  targets -- 目标节点列表
     */
    default void processTarget(RouteElement target, List<Target> targets, RouteModel routeModel){
        targets.add(target);
    }
}
