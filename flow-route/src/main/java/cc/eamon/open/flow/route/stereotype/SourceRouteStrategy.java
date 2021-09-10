package cc.eamon.open.flow.route.stereotype;

import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import org.apache.velocity.app.VelocityEngine;

import java.util.List;
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

    void setEngine(VelocityEngine engine);

}
