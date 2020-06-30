package cc.eamon.open.flow.core.Proxy;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: lucas
 * @date: 2020/6/28
 * @email: lucas@eamon.cc
 */
public class ProxyFactory implements MethodInterceptor {
    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     * @return
     */
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    /**
     * 对目标进行增强
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {


        //TODO:注入参数以及参数匹配

        //TODO：调用目标方法
        Object returnValue = method.invoke(target, args);


        //TODO:根据当前结果获取到执行结果
        //TODO:继续调用执行下一个流程
        System.out.println("后置处理...");

        return returnValue;
    }
}
