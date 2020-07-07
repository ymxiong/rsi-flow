package cc.eamon.open.flow.core.route.strategy;

import java.util.List;

import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

public interface Strategy {
	/**
	 * @Desc 寻找下一节点（可能有多个）
	 * @param currentConfig -- 当前节点
	 * @return 下一节点的id
	 */
	List<String> findWay(RouteConfig currentConfig);
}
