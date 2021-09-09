package cc.eamon.open.flow.route.stereotype;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:16
 */
public interface Strategy {

    Source getSource();

    RouteStrategy getStrategy();

}
