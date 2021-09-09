package cc.eamon.open.flow.route.stereotype;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-07 12:18:14
 */
public interface Route {

    /*
     * get source
     */
    Source getSource();

    /*
     * get target
     */
    Target getTarget();

    /*
     * get condition
     */
    Condition getCondition();
}
