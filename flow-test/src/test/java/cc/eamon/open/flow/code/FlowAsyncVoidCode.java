package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncVoidCode {

    @Flow(value = "FLOW_KINDERGARTEN")
    public void FLOW_KINDERGARTEN(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完幼儿园长大到6岁啦");
        FlowEngine.instance().register("age", age);
    }


    @Flow(value = "FLOW_PRIMARYSCHOOL")
    public void FLOW_PRIMARYSCHOOL(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完小学长大" + age + "岁啦");
        FlowEngine.instance().register("age", age);
    }


    @Flow(value = "FLOW_SECONDARYSCHOOL")
    public void FLOW_SECONDARYSCHOOL(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完中学长大" + age + "岁啦");
        FlowEngine.instance().register("age", age);
    }


    @Flow(value = "FLOW_UNIVERSITY")
    public void FLOW_UNIVERSITY(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完大学长大" + age + "岁啦");
        FlowEngine.instance().register("age", age);
    }


    @Flow(value = "FLOW_MARRY")
    public void FLOW_MARRY(String name, Integer age) {
        age = age + 2;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + name + "结完婚" + age + "岁啦");
        FlowEngine.instance().register("age", age);
    }


    @Flow(value = "FLOW_FIRSTJOB")
    public void FLOW_FIRSTJOB(String name) {
        Integer money = 20000;
        System.out.println(Thread.currentThread().getName() + ":" + name + "第一份工作月薪" + money + "啦");
        FlowEngine.instance().register("money", money);
    }

    @Flow(value = "FLOW_SECONDJOB")
    public void FLOW_SECONDJOB(String name, Integer money) {
        money += 20000;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + name + "第二份工作月薪" + money + "啦");
        FlowEngine.instance().register("money", money);
    }


}
