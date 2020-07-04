package cc.eamon.open.flow.core;

import com.googlecode.aviator.AviatorEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RouteTest {

    @Test
    public void testParse() throws IOException {
        //获取json字符串
//        String json = JsonUtil.getJson("/test.json");
//        //解析json字符串
//        List<ProcessNode> nodes = RouteEngine.parseJson(json);
//        System.out.println();
    }
    
    //测试 Aviator 调用函数
    @Test
    public void testAviator() {
    	String string = "123456";
    	Map<String, Object> map = new HashMap<>();
    	map.put("string",string);
    	String code = "string==\"123456\"";
    	boolean result = (boolean) AviatorEvaluator.execute(code, map);
    	TestCase.assertTrue(result);
    }
    
//    //测试获取下一节点
//    @Test
//    public void testGetNextNode() throws Exception {
//      //获取JSON字符串
//      String json = JsonUtils.getJson("/test.json");
//      RouteEngine routeEngine = new RouteEngine();
//      //解析JSON字符串，放入路由
//      List<FlowNode> nodes = routeEngine.parseJson(json);
//      FlowContextHolder.get().setProcessNodes(nodes);
//      //放入变量
//      Map<String, Object> map = new HashMap<>();
//      map.put("usr", "xsy");
//      map.put("pwd", "123");
//      FlowContextHolder.get().setVariableMap(map);
//      //测试路由
//      String startNode = "sID";
//      System.out.println("ID: "+startNode + " name: " + "S");
//      List<FlowNode> next_1 = routeEngine.getNextNode(startNode);
//      startNode = next_1.get(0).getNodeId();
//      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
//      next_1 = routeEngine.getNextNode(startNode);
//      startNode = next_1.get(0).getNodeId();
//      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
//      next_1 = routeEngine.getNextNode(startNode);
//      startNode = next_1.get(0).getNodeId();
//      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
//    }
    
}
