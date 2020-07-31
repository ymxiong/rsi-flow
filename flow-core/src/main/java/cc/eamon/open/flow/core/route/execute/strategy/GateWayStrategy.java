package cc.eamon.open.flow.core.route.execute.strategy;

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

		Map<String, Object> env = getEnv();
		//挑选符合条件的出口
		for(TargetConfig targetConfig : targets) {
			String condition = targetConfig.getCondition();//条件
			String target = targetConfig.getTarget();//出口id

			if(judgeCondition(condition,env)) {
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

    /**
     * 判断条件是否正确
     *
     * @author  xsy
     * @param  condition -- 条件
     */
    private boolean judgeCondition(String condition, Map<String, Object> env){
		//这里需要对表达式进行判断，先简单判断一下
		return (boolean) (Boolean) JexlUtils.invokeMethod(condition,env);
	}
}
