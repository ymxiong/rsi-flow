package cc.eamon.open.flow.route.stereotype;

import cc.eamon.open.flow.route.result.RouteResult;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:34:31
 */
public interface SourceRouteStrategy {

    String getSRType();

    Source getSource();

    RouteResult route(Source source, Map<String, Object> variables);

    void addRoute(Route route);

}
