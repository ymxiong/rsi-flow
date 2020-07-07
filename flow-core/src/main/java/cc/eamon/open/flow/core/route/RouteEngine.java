package cc.eamon.open.flow.core.route;

import cc.eamon.open.flow.core.context.FlowContextHolder;

import java.util.Map;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
 */
public class RouteEngine {

	
	//将当前上下文的所有变量都存入Map中
	private Map<String, Object> getAllVariables(){
		return FlowContextHolder.get().getVariableMap();
	}

}
