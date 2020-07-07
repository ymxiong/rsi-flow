package cc.eamon.open.flow.core.stereotype.config;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class TargetConfig {
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

    @Override
    public String toString() {
        return "Target{" +
                "condition='" + condition + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
