package cc.eamon.open.flow.route.util;

import cc.eamon.open.flow.route.enums.ConditionEnums;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.util.Assert;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionHelper {

    public static List<Target> AeEngine(Source source, List<Route> routes, Map<String, Object> variables, VelocityEngine engine) {
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


    public static List<Target> JexlEngine(Source source, List<Route> routes, Map<String, Object> variables) {
        List<Target> targets = new ArrayList<>();
        Target defaultTarget = null;
        for (Route route : routes) {
            if (ConditionEnums.DEFAULT.getId().equals(route.getCondition().getExpression())) {
                defaultTarget = route.getTarget();
                continue;
            }
            Boolean success = (Boolean) JexlUtils.invokeMethod(route.getCondition().getExpression(), variables);
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
