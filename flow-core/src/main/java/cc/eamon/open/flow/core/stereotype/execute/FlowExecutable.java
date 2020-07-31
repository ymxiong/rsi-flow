package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;

import java.util.List;

public interface FlowExecutable {
    void execute();

    void executeNext(List<Source> sources);

    void executeNext(Source source);

    void executeNextNode(Target target);

    void executeNextNodes(List<Target> targets);
}
