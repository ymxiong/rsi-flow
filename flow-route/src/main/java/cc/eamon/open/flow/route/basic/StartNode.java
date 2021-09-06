package cc.eamon.open.flow.route.basic;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Source;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 18:45:42
 */
public class StartNode implements Source {
    @Override
    public String getId() {
        return RouteEnums.START.getId();
    }
}
