package cc.eamon.open.flow.core.route;

import java.util.ArrayList;
import java.util.List;

import cc.eamon.open.flow.core.route.enums.StrategyType;
import cc.eamon.open.flow.core.route.pojo.RouteConfigPojo;
import cc.eamon.open.flow.core.route.strategy.Strategy;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
 */
public class RouteEngine implements Route{

	private List<RouteConfig> routeConfigs;
	private Strategy strategy;
	
	
	public RouteEngine(List<RouteConfig> routeConfigs) {
		this.routeConfigs = routeConfigs;
	}

	@Override
	public List<Target> route(Source source) {
		
		List<Target> targets = new ArrayList<>();
		
		//找到对应ID的当前节点
		RouteConfig currentConfig = null;
		for(RouteConfig config : routeConfigs) {
			if(config.getId().equals(source.getId())) {
				currentConfig = config;
				break;
			}
		}
		
		//寻找对应策略
		strategy = StrategyType.getStrategy(currentConfig.getType());
		
		//获取下一节点的id和name，封装成Target
		List<String> nextIds = strategy.findWay(currentConfig);
		if(nextIds != null) {
			for(String id : nextIds) {
				for(RouteConfig config : routeConfigs) {
					if(config.getId().equals(id)) {
						Target target = new RouteConfigPojo(config);
						targets.add(target);
					}
				}
			}
		}
		return targets;
	}

	@Override
	public List<Target> route(List<Source> sources) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
