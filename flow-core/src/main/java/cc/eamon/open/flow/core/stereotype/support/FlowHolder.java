package cc.eamon.open.flow.core.stereotype.support;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.Route;
import cc.eamon.open.flow.core.route.RouteEngine;
import cc.eamon.open.flow.core.route.Source;
import cc.eamon.open.flow.core.route.Target;
import cc.eamon.open.flow.core.route.pojo.RouteConfigPojo;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-07-01 20:18:53
 */
public class FlowHolder {

	private FlowConfig flowConfig;

	// private List<FlowCode> flowCodes;

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
	public void execute() {
		// TODO:需要修改，目前是固定执行所有FlowCode，需要加入流程顺序相关东西
		FlowContextHolder.init();
		for (RouteConfig routeConfig : flowConfig.getRoutes()) {
			if (routeConfig.getType().equals("start")) {
				// 这里后续可能改动
				Target startTarget = new RouteConfigPojo(routeConfig);// 待执行目标
				execute(startTarget);
				break;
			}
		}
	}

	// 可能改成execute(RouteConfigHolder routeConfigHolder)
	private void execute(Target target) {


		FlowCode flowCode = flowCodes.get(target.getName());
		Method method = flowCode.getMethod();
		// 注入流程代码所需参数
		// 拿到参数名称以及类型
		Class<?>[] parameterClasses = method.getParameterTypes();
		DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
		String[] parameterNames = discover.getParameterNames(method);

		// 注入参数
		assert parameterNames != null;
		int parameterNum = parameterNames.length;
		Object[] args = new Object[parameterNum];

		for (int i = 0; i < parameterNum; i++) {
			if (!parameterClasses[i].equals(FlowContextHolder.get().getVariableTypeMap().get(parameterNames[i]))) {
				// TODO: 参数匹配
				System.out.println("参数不匹配");
			}
			args[i] = FlowContextHolder.get().getVariableMap().get(parameterNames[i]);
			// TODO：如果是END节点就需要将结果注入父ThreadLocal里面(如果有的话)可能使用后置代理来做
		}

		flowCode.execute(args);

		// TODO 不知道路由引擎应该在何处生成
		RouteConfig routeConfig = new RouteConfig();
		routeConfig.setId(target.getId());
		Source source = new RouteConfigPojo(routeConfig);
		List<Target> targets = route.route(source);
		execute(targets);

	}

	
	private void execute(List<Target> targets) {

		if(targets.isEmpty())
			return;

		for(Target target : targets) {
			execute(target);
		}
	}
}
