package cc.eamon.open.flow.core.route.strategy;

import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;

import java.util.ArrayList;
import java.util.List;

public class EventStrategy implements RouteStrategy {

	@Override
	public List<String> findWay(RouteConfig currentConfig) {
		List<String> results = new ArrayList<>();
		//目标
		List<TargetConfig> targets = currentConfig.getTargets();
		//取第一个目标
		results.add(targets.get(0).getTarget());
		return results;
	}

}
