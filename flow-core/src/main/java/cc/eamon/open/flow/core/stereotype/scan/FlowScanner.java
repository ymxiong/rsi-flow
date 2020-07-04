package cc.eamon.open.flow.core.stereotype.scan;

import cc.eamon.open.flow.core.stereotype.definition.FlowCode;

import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:08:13
 */
public interface FlowScanner {

    Set<? extends FlowCode> scan(String... basePackages);

}
