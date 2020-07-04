package cc.eamon.open.flow.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.RouteEngine;
import cc.eamon.open.flow.core.route.entity.ProcessNode;
import cc.eamon.open.flow.core.util.JsonUtil;
import cc.eamon.open.flow.remote.entity.User;
import junit.framework.TestCase;


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
    public void testAviator() throws IllegalAccessException, NoSuchMethodException {
    	User user = new User();
    	Map<String, Object> map = new HashMap<>();
    	map.put("user",user);
    	AviatorEvaluator.addInstanceFunctions("User", User.class);
    	String code = "User.setId(user,\"123\")";
    	AviatorEvaluator.execute(code,map);
    	code = "User.getId(user)==\"123\"";
    	boolean result = (boolean) AviatorEvaluator.execute(code, map);
    	System.out.println(user.getId());
    	TestCase.assertTrue(result);
    }
    
    //测试获取下一节点
    @Test
    public void testGetNextNode() throws Exception {
      //获取JSON字符串
      String json = JsonUtil.getJson("/test.json");
      RouteEngine routeEngine = new RouteEngine();
      //解析JSON字符串，放入路由
      List<ProcessNode> nodes = routeEngine.parseJson(json);
      FlowContextHolder.get().setProcessNodes(nodes);
      //放入变量
      Map<String, Object> map = new HashMap<>();
      map.put("usr", "xsy");
      map.put("pwd", "123");
      FlowContextHolder.get().setVariableMap(map);
      //测试路由
      String startNode = "sID";
      System.out.println("ID: "+startNode + " name: " + "S");
      List<ProcessNode> next_1 = routeEngine.getNextNode(startNode);
      startNode = next_1.get(0).getNodeId();
      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
      next_1 = routeEngine.getNextNode(startNode);
      startNode = next_1.get(0).getNodeId();
      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
      next_1 = routeEngine.getNextNode(startNode);
      startNode = next_1.get(0).getNodeId();
      System.out.println("ID: "+startNode + " name: " + next_1.get(0).getName());
    }
    
   
}
