package cc.eamon.open.flow.core.route.model;

import cc.eamon.open.flow.core.route.Source;
import cc.eamon.open.flow.core.route.Target;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

/**
 * 
 * @author xsy
 * @desc: 用于封装RouteConfig底层数据结构
 */
public class RouteConfigModel implements Source, Target{
	
	private RouteConfig routeConfig;

	public RouteConfigModel(RouteConfig routeConfig) {
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
