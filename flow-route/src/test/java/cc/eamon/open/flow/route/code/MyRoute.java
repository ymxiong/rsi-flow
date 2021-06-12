package cc.eamon.open.flow.route.code;

import cc.eamon.open.flow.route.stereotype.Condition;
import cc.eamon.open.flow.route.stereotype.Route;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 17:54:34
 */
public class MyRoute implements Route {

    private Node source;

    private Node target;

    private Condition condition;

    public MyRoute(Node source, Node target, Condition condition) {
        this.source = source;
        this.target = target;
        this.condition = condition;
    }

    @Override
    public Node getSource() {
        return source;
    }

    @Override
    public Node getTarget() {
        return target;
    }

    @Override
    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "MyRoute{" +
                "source='" + source.getId() + '\'' +
                ", target='" + target.getId() + '\'' +
                ", condition='" + condition.getId() + '\'' +
                '}';
    }
}
