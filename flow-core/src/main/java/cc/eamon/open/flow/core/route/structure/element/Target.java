package cc.eamon.open.flow.core.route.structure.element;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 21:17:46
 */
public interface Target {
    /*
     * get current route id
     */
    String getId();

    /*
     * get current route name
     */
    String getName();

    /*
     * get the target type
     */
    String getType();

    /*
     * get the reference
     */
    String getReference();
}
