package cc.eamon.open.flow.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;

import cc.eamon.open.flow.remote.entity.User;
import junit.framework.TestCase;


public class RouteTest {

    @Test
    public void testParse() throws IOException {
//        //获取json字符串
//        String json = JsonUtil.getJson("/test.json");
//        //解析json字符串
//        List<ProcessNode> nodes = RouteEngine.parseJson(json);
//        System.out.println();
    }
    
    //测试 Aviator 调用函数
    @Test
    public void testAviator() {
    	User user = new User();
    	Map<String, Object> map = new HashMap<>();
    	map.put("user",user);
    	String code = "user.getId()==\"123456\"";
    	boolean result = (boolean) AviatorEvaluator.execute(code, map);
    	TestCase.assertTrue(result);
    }
    
}
