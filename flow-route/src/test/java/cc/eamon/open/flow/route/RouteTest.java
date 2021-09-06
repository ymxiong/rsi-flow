package cc.eamon.open.flow.route;

import cc.eamon.easyfile.FileTools;
import cc.eamon.open.flow.route.code.Collector;
import cc.eamon.open.flow.route.code.MyRoute;
import cc.eamon.open.flow.route.code.Node;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 17:36:17
 */
public class RouteTest {

    @Test
    public void testRoute() {
        Routes routes = new Routes();
        Collector collector = new Collector();
        URL url = RouteTest.class.getClassLoader().getResource("route.json");
        if (url == null) return;
        String route = FileTools.loadFile(new File(url.getFile()));
        JSONObject object = JSONObject.parseObject(route);
        for (int i = 0; i < object.getJSONArray("routes").size(); i++) {
            String source = object.getJSONArray("routes").getJSONObject(i).getString("source");
            String target = object.getJSONArray("routes").getJSONObject(i).getString("target");
            String condition = object.getJSONArray("routes").getJSONObject(i).getString("condition");
            collector.loadNode(new Node(source), new Node(target));
//            collector.loadCondition(() -> condition);
        }

        for (int i = 0; i < object.getJSONArray("routes").size(); i++) {
            String source = object.getJSONArray("routes").getJSONObject(i).getString("source");
            String target = object.getJSONArray("routes").getJSONObject(i).getString("target");
            String condition = object.getJSONArray("routes").getJSONObject(i).getString("condition");
            routes.load(new MyRoute(collector.getNode(source), collector.getNode(target), collector.getCondition(condition)));
        }

        routes.load(
                collector.getCondition("bp>180||bp>110")
//                , collector.getExpression("头痛视力缺损")
                , collector.getCondition("胸痛")
        );
        for (Target start : routes.execute(collector.getNode(RouteEnums.START.getId()), new HashMap<>())) {
            System.out.println(start.getId());
        }
    }

}
