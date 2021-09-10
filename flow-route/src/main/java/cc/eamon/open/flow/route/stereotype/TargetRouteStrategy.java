package cc.eamon.open.flow.route.stereotype;

import cc.eamon.open.flow.route.result.RouteResult;
import org.apache.velocity.app.VelocityEngine;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:34:31
 */
public interface TargetRouteStrategy {

    String getTRType();

    Target getTarget();

    RouteResult route(RouteResult routeResult);

}
