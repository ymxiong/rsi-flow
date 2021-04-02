package cc.eamon.open.flow.config;

/**
 * Author: lucas
 * Date: 2020/7/5
 * Email: lucas@eamon.cc
 */
public class TargetConfig {

    /**
     * 目标节点
     */
    private String target;

    /**
     * 到目标节点的条件
     */
    private String condition;

    /**
     * 目标条件所处域
     */
    private String scope;

    public TargetConfig() {
    }

    public TargetConfig(String target, String condition) {
        this.target = target;
        this.condition = condition;
    }

    public TargetConfig(String target, String condition, String scope) {
        this.target = target;
        this.condition = condition;
        this.scope = scope;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
