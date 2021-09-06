package cc.eamon.open.flow.route;

import cc.eamon.open.flow.route.enums.ConditionEnums;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.util.Assert;

import java.io.StringWriter;
import java.util.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-07 11:42:41
 */
public class Routes {

    // Initialize the engine.
    private VelocityEngine engine;

    /**
     * 流程来源路由容器
     */
    private Map<String, List<Route>> sourceRouteGroup = new HashMap<>();

    /**
     * 流程目标路由容器
     */
    private Map<String, List<Route>> targetRouteGroup = new HashMap<>();


    public Routes() {
        engine = new VelocityEngine();
        engine.setProperty(Velocity.RESOURCE_LOADERS, "string");
        engine.setProperty("resource.loader.string.class", StringResourceLoader.class.getName());
        engine.init();
    }

    public void load(Route... routes) {
        load(Arrays.asList(routes));
    }

    public void load(List<Route> routes) {
        for (Route route : routes) {
            sourceRouteGroup.putIfAbsent(route.getSource().getId(), new ArrayList<>());
            sourceRouteGroup.computeIfPresent(route.getSource().getId(), ((s, sources) -> {
                sources.add(route);
                return sources;
            }));
            targetRouteGroup.putIfAbsent(route.getTarget().getId(), new ArrayList<>());
            targetRouteGroup.computeIfPresent(route.getTarget().getId(), ((s, targets) -> {
                targets.add(route);
                return targets;
            }));
        }

    }


    // 只有一个
    public List<Target> execute(Source source, Map<String, Object> variables) {
        List<Route> routes = sourceRouteGroup.get(source.getId());
        List<Target> targets = new ArrayList<>();

        Target defaultTarget = null;

        for (Route route : routes) {
            if (ConditionEnums.DEFAULT.getId().equals(route.getCondition().getExpression())) {
                defaultTarget = route.getTarget();
                continue;
            }
            // 校验condition
            // Add template to repository
            StringResourceRepository repository = StringResourceLoader.getRepository();
            repository.putStringResource(route.getCondition().getId(), route.getCondition().getExpression());

            // Set parameters
            VelocityContext context = new VelocityContext();

            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                context.put(entry.getKey(), entry.getValue().toString());
            }

            // Process the template
            StringWriter writer = new StringWriter();
            engine.getTemplate(route.getCondition().getId()).merge(context, writer);

            Boolean success = (Boolean) AviatorEvaluator.execute(writer.toString());
            if (success) {
                targets.add(route.getTarget());
            }
        }

        if (targets.size() == 0 && defaultTarget != null) {
            targets.add(defaultTarget);
        }

        Assert.isTrue(targets.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());

        return targets;
    }

}
