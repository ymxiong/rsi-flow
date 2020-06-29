package cc.eamon.open.flow.core;

import java.io.IOException;
import java.util.List;

import cc.eamon.open.flow.core.route.RouteEngine;
import cc.eamon.open.flow.core.route.entity.ProcessNode;
import cc.eamon.open.flow.core.util.JsonUtil;

public class RouteTest {

    @org.junit.Test
    public void testParse() throws IOException {
        //获取json字符串
        String json = JsonUtil.getJson("/test.json");
        //解析json字符串
        List<ProcessNode> nodes = RouteEngine.parseJson(json);
        System.out.println();
    }
}
