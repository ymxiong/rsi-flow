package cc.eamon.open.flow.core.route.strategy;

import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

import java.util.List;

public interface Strategy {
	/**
	 * @Desc 寻找下一节点（可能有多个）
	 * @param currentConfig -- 当前节点
	 * @return 下一节点的id
	 */
	List<String> findWay(RouteConfig currentConfig);
}
