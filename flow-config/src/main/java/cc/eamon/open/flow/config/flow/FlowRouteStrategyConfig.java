package cc.eamon.open.flow.config.flow;

import java.io.Serializable;

/**
 * Author: lucas
 * Date: 2020/7/5
 * Email: lucas@eamon.cc
 */
public class FlowRouteStrategyConfig implements Serializable {


    private static final long serialVersionUID = 3375256546663515066L;
    /**
     * 源节点
     */
    private String source;

    /**
     * 目标节点
     */
    private String target;


    /**
     * 到目标节点的条件
     */
    private String strategy = "normal";

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
