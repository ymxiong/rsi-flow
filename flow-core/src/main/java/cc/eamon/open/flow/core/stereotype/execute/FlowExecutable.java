package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;

import java.util.List;

public interface FlowExecutable {
    void execute() throws Exception;

    void executeNext(List<Source> sources) throws Exception;

    void executeNext(Source source) throws Exception;

    void executeNextNode(Target target) throws Exception;

    void executeNextNodes(List<Target> targets) throws Exception;
}
