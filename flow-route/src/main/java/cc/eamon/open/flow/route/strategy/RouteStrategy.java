package cc.eamon.open.flow.route.strategy;

import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:34:31
 */
public interface RouteStrategy {

    List<Target> route(Source source);

}
