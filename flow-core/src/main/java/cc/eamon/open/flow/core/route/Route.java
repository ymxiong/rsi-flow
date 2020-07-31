package cc.eamon.open.flow.core.route;

import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 21:16:24
 */
public interface Route {

    List<Target> route(Source source);

    List<Target> route(List<Source> sources);

    Target getStartNode();


}
