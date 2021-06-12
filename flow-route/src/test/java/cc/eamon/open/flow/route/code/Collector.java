package cc.eamon.open.flow.route.code;

import cc.eamon.open.flow.route.stereotype.Condition;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 17:41:36
 */
public class Collector {

    private Map<String, Node> nodeMap = new HashMap<>();

    private Map<String, Condition> conditionMap = new HashMap<>();

    public void loadNode(Node... nodes) {
        for (Node node : nodes) {
            nodeMap.put(node.getId(), node);
        }
    }

    public Node getNode(String id) {
        return nodeMap.get(id);
    }

    public void loadCondition(Condition... conditions) {
        for (Condition condition : conditions) {
            conditionMap.put(condition.getId(), condition);
        }
    }

    public Condition getCondition(String id) {
        return conditionMap.get(id);
    }

}
