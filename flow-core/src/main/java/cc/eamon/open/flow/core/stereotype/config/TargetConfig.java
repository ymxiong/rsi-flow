package cc.eamon.open.flow.core.stereotype.config;

/**
 * Author: lucas
 * Date: 2020/7/5
 * Email: lucas@eamon.cc
 */
public class TargetConfig {
    private String scope;

    //到目标节点的条件
    private String condition;

    private String target;

    public TargetConfig(String condition, String target) {
        this.condition = condition;
        this.target = target;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Target{" +
                "scope='" + scope + '\'' +
                ",condition='" + condition + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
