package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.route.Route;
import cc.eamon.open.flow.core.route.RouteEngine;
import cc.eamon.open.flow.core.route.Source;
import cc.eamon.open.flow.core.route.Target;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:18:53
 */
public class FlowHolder implements FlowExecutable,Source,Target{

    private FlowConfig flowConfig;

    private Map<String, FlowCode> flowCodes;

	private Route route;

    public FlowHolder(FlowConfig flowConfig, Map<String, FlowCode> flowCodes) {
        this.flowConfig = flowConfig;
        this.flowCodes = flowCodes;
		route = new RouteEngine(flowConfig.getRoutes());
    }

    public String getFlowName() {
        return flowConfig.getName();
    }

    public Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }

	// TODO: NEED OPTIMIZE THREAD RUN
	@Override
	public void execute() {
//		FlowContextHolder.init();
		for (RouteConfig routeConfig : flowConfig.getRoutes()) {
			if (routeConfig.getType().equals("start")) {
				// 这里后续可能改动
				FlowNode startNode=new FlowNode(flowCodes.get(routeConfig.getName()),
						routeConfig.getId(),this);
				startNode.execute();
//				Target startTarget = new RouteConfigPojo(routeConfig);// 待执行目标
//				execute(startTarget);
				break;
			}
		}
	}


	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getName() {
		return flowConfig.getName();
	}

	public Route getRoute() {
		return route;
	}

}


