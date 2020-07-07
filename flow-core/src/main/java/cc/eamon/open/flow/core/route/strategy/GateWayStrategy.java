package cc.eamon.open.flow.core.route.strategy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.aviator.AviatorEvaluator;

import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;

public class GateWayStrategy implements Strategy {

	@Override
	public List<String> findWay(RouteConfig currentConfig) {
		List<String> results = new ArrayList<>();
		//目标
		List<TargetConfig> targets = currentConfig.getTargets();
		//挑选符合条件的出口
		for(TargetConfig targetConfig : targets) {
			String condition = targetConfig.getCondition();//条件
			String target = targetConfig.getTarget();//出口id
			
			//这里需要对表达式进行判断，先简单判断一下
			boolean res = (Boolean)AviatorEvaluator.execute(condition);
			
			if(res) {
				results.add(target);
			}
		}
		return results;
	}

}
