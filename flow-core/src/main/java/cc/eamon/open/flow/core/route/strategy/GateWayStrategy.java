package cc.eamon.open.flow.core.route.strategy;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;
import cc.eamon.open.flow.core.util.JexlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GateWayStrategy implements RouteStrategy {

	@Override
	public List<String> findWay(RouteElement routeElement) {
		List<String> results = new ArrayList<>();
		//目标
		List<TargetConfig> targets = routeElement.getTargetConfigs();
		//挑选符合条件的出口
		for(TargetConfig targetConfig : targets) {
			String condition = targetConfig.getCondition();//条件
			String target = targetConfig.getTarget();//出口id

            Map<String, Object> env = getEnv();

			//这里需要对表达式进行判断，先简单判断一下
			boolean res = (Boolean) JexlUtils.invokeMethod(condition,env);

			if(res) {
				results.add(target);
			}
		}
		return results;
	}


	/**
	 * 获取当前环境
	 *
	 * @author  xsy
	 */
	private Map<String, Object> getEnv(){
	    return new FlowContextExtractor().getVariableMap();
    }
	
}
