package cc.eamon.open.flow.route;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.route.code.Collector;
import cc.eamon.open.flow.route.code.MyRoute;
import cc.eamon.open.flow.route.code.Node;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Condition;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 17:36:17
 */
public class RouteTest {

    private Routes routes = new Routes();

    @Test
    public void testRoute() {
        Collector collector = new Collector();
        URL url = RouteTest.class.getClassLoader().getResource("route.json");
        if (url == null) return;
        String route = FileTools.loadFile(new File(url.getFile()));
        JSONObject object = JSONObject.parseObject(route);
        for (int i = 0; i < object.getJSONArray("routes").size(); i++) {
            String source = object.getJSONArray("routes").getJSONObject(i).getString("source");
            String target = object.getJSONArray("routes").getJSONObject(i).getString("target");
            collector.loadNode(new Node(source), new Node(target));
        }

        for (int i = 0; i < object.getJSONArray("routes").size(); i++) {
            String source = object.getJSONArray("routes").getJSONObject(i).getString("source");
            String target = object.getJSONArray("routes").getJSONObject(i).getString("target");
            int finalI = i;
            Condition condition = new Condition() {
                @Override
                public String getId() {
                    return source + "-" + target;
                }

                @Override
                public String getExpression() {
                    return object.getJSONArray("routes").getJSONObject(finalI).getString("condition");
                }
            };
            routes.load(new MyRoute(collector.getNode(source), collector.getNode(target), condition));
        }

//        routes.load(
//                collector.getCondition("bp>180||bp>110")
////                , collector.getExpression("头痛视力缺损")
//                , collector.getCondition("胸痛")
//        );
        Map<String, Object> data = new HashMap<>();
        data.put("sbp", 10);
        data.put("dbp", 10);
        execute(RouteEnums.START::getId, data);

    }

    private void execute(Source source, Map<String, Object> paramMap) {
        // 1.1 找到当前需要执行的节点
        // 1.2 向route注入流程参数，放入route context
        List<Target> targets = routes.execute(source, paramMap);
        System.out.println("=========start " + source.getId() + "=========");
        for (Target target : targets) {
            System.out.println(source.getId() + " => " + target.getId());
        }
        System.out.println("=========end " + source.getId() + "=========");
        for (Target target : targets) {
            if (target.getId().equals(RouteEnums.END.getId())) continue;

            // 1.3 代码执行, holder会自动装配
            Node holder = (Node) target;

            // 1.4 逻辑执行，与代码执行解构
            execute(holder, paramMap);
        }
    }

}
