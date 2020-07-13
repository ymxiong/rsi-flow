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

//	private void execute(Target target) {
//		FlowCode flowCode = flowCodes.get(target.getName());
//
//		Method method = flowCode.getMethod();
//		// 注入流程代码所需参数
//		// 拿到参数名称以及类型
//		Class<?>[] parameterClasses = method.getParameterTypes();
//		DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
//		String[] parameterNames = discover.getParameterNames(method);
//
//		// 注入参数
//		assert parameterNames != null;
//		int parameterNum = parameterNames.length;
//		Object[] args = new Object[parameterNum];
//
//		for (int i = 0; i < parameterNum; i++) {
//			if (!parameterClasses[i].equals(FlowContextHolder.get().getVariableTypeMap().get(parameterNames[i]))) {
//				// TODO: 参数匹配
//				System.out.println("参数不匹配");
//			}
//			args[i] = FlowContextHolder.get().getVariableMap().get(parameterNames[i]);
//		}
//
//		flowCode.execute(args);
//
//		RouteConfig routeConfig = new RouteConfig();
//		routeConfig.setId(target.getId());
//		Source source = new RouteConfigPojo(routeConfig);
//		List<Target> targets = route.route(source);
//		execute(targets);
//	}


//	private void execute(List<Target> targets) {
//		//没有目标了
//		if(targets.isEmpty())
//			return;
//		if (targets.size()<=1){
//			execute(targets.get(0));
//		}else {
//			//TODO:网关并行处理
//			for (Target target : targets) {
////				FlowThread flowThread=new FlowThread(target,FlowContextHolder.get());
//				execute(target);
//			}
//		}
//	}




}


