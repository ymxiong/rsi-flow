package cc.eamon.open.flow.route.basic;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 18:45:42
 */
public class EndNode implements Target {
    @Override
    public String getId() {
        return RouteEnums.END.getName();
    }
}
