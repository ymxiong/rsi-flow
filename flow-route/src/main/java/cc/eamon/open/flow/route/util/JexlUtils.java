package cc.eamon.open.flow.route.util;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.Map;

/**
 * author : xsy
 * description: Jexl 表达式执行引擎工具
 * date : 2020/7/10
 */
public class JexlUtils {

    /**
     * 根据环境执行java表达式
     *
     * @param exp -- 待执行的java表达式
     * @param map -- 执行环境（变量，常量，对象）
     * @return 执行结果
     * @author xsy
     */
    public static Object invokeMethod(String exp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(exp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        if (null == e.evaluate(jc)) {
            return "";
        }
        return e.evaluate(jc);
    }


    /**
     * 从环境中取出参数
     *
     * @param exp -- 待执行的java表达式
     * @param map -- 执行环境（变量，常量，对象）
     * @return 执行结果
     * @author xsy
     */
    public static Object getVariable(String exp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(exp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        return e.evaluate(jc);
    }
}
