package cc.eamon.open.flow.core.route;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 21:16:24
 */
public interface Route {

    Target route(Source source);

    Target route(List<Source> sources);

}
