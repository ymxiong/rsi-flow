package cc.eamon.open.flow.core.route.enums;

import cc.eamon.open.flow.core.route.strategy.Strategy;
import cc.eamon.open.flow.core.route.strategy.EndEventStrategy;
import cc.eamon.open.flow.core.route.strategy.EventStrategy;
import cc.eamon.open.flow.core.route.strategy.GateWayStrategy;

/**
 * 
 * @author xsy
 * @Desc 路由策略枚举
 */
public enum StrategyType {

	START_EVENT("start", new EventStrategy()),
	EVENT("event", new EventStrategy()),//事件策略
	END_EVENT("end", new EndEventStrategy()),//结束策略
	GATEWAY("gateway", new GateWayStrategy());//网关策略
	
	private String type;
	private Strategy strategy;
	

	StrategyType(String type, Strategy strategy) {
		this.type = type;
		this.strategy = strategy;
	}

	//获取对应枚举的策略
	public static Strategy getStrategy(String type) {
		for(StrategyType strategyType : StrategyType.values()) {
			if(strategyType.type.equals(type)) {
				return strategyType.strategy;
			}
		}
		return null;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public Strategy getStrategy() {
		return strategy;
	}


	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}
