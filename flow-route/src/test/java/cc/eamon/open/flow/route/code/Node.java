package cc.eamon.open.flow.route.code;

import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 17:42:46
 */
public class Node implements Source, Target {

    private String id;

    public Node(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

}
