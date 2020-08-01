package cc.eamon.open.flow.core.route;

import cc.eamon.open.flow.example.remote.entity.User;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试Jexl
 *
 * author     : xsy
 */
public class JexlTest {

    private Map<String, Object> env;

    @BeforeTest
    public void before(){
        env = new HashMap<>();
        env.put("id","123456");
        env.put("user",new User());
        env.put("String",String.class);

        List<OrderItem> orderItems = Arrays.asList(
                new OrderItem("apple",10),
                new OrderItem("banana",10.5),
                new OrderItem("milk",8));
        env.put("order", new Order(orderItems));
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

    //测试简单的表达式
    @Test
    public void TestSimpleExpression(){
        String expression = "id.equals('123456') && user!=null";
        boolean res = (boolean) invokeMethod(expression,env);
        Assert.assertTrue(res);
    }


    //测试调用对象内的方法
    @Test
    public void TestFunctionMethod(){
        String expression="user.getId().equals(id)";
        boolean res = (boolean) invokeMethod(expression,env);
        Assert.assertTrue(res);
    }

    //测试调用静态方法（需要注入静态类）
    @Test
    public void TestStaticFunction(){
        String expression = "String.valueOf(123)";
        String res = (String) invokeMethod(expression,env);
        Assert.assertEquals("123",res);
    }

    //测试嵌套调用【嵌套调用不成功】
    @Test
    public void testNestFunction(){
        String expression = "(order.getOrderItems().size())==3";
        boolean res = (boolean) invokeMethod(expression,env);
        Assert.assertTrue(res);
    }

}

//订单
class Order{
    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}

//订单项
class OrderItem{
    String itemName;
    double price;

    public OrderItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}