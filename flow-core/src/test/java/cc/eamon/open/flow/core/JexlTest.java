package cc.eamon.open.flow.core;

import cc.eamon.open.flow.remote.entity.User;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author     : xsy
 * @description: 测试Jexl
 * @date       : 2020/7/10
 */
public class JexlTest {

    private Map<String, Object> env;

    @Before
    public void before(){
        env = new HashMap<>();
        env.put("id","123456");
        env.put("user",new User());
    }

    //动态加载方法
    private Object invokeMethod(String jexlExp, Map<String,Object> map){
        JexlEngine jexl=new JexlEngine();
        Expression e = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for(String key:map.keySet()){
            jc.set(key, map.get(key));
        }
        if(null==e.evaluate(jc)){
            return "";
        }
        return e.evaluate(jc);
    }


    @Test
    public void TestFunctionMethod(){
        String expression="user.getId().equals(id)";
        boolean res = (boolean) invokeMethod(expression,env);
        Assert.assertTrue(res);
    }
}
