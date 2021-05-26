package cc.eamon.open.flow.config.flow;

import java.io.Serializable;

/**
 * Author: lucas
 * Date: 2020/7/5
 * Email: lucas@eamon.cc
 */
public class FlowRouteConfig implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6604021116438509087L;

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
    private String condition = "true";

    /**
     * 目标条件所处域
     */
    private String scope;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
