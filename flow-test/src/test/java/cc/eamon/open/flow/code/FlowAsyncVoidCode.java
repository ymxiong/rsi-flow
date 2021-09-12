package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncVoidCode {

    @Flow(value = "FLOW_KINDERGARTEN", retrieve = "age")
    public Integer FLOW_KINDERGARTEN(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完幼儿园长大到6岁啦");
        return age;
    }


    @Flow(value = "FLOW_PRIMARYSCHOOL", retrieve = "age")
    public Integer FLOW_PRIMARYSCHOOL(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完小学长大" + age + "岁啦");
        return age;
    }


    @Flow(value = "FLOW_SECONDARYSCHOOL", retrieve = "age")
    public Integer FLOW_SECONDARYSCHOOL(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完中学长大" + age + "岁啦");
        return age;
    }


    @Flow(value = "FLOW_UNIVERSITY", retrieve = "age")
    public Integer FLOW_UNIVERSITY(String name, Integer age) {
        age = age + 6;
        System.out.println(Thread.currentThread().getName() + ":" + name + "读完大学长大" + age + "岁啦");
        return age;
    }


    @Flow(value = "FLOW_MARRY", retrieve = "age")
    public Integer FLOW_MARRY(String name, Integer age) {
        age = age + 2;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + name + "结完婚" + age + "岁啦");
        return age;
    }


    @Flow(value = "FLOW_FIRSTJOB", retrieve = "age")
    public Integer FLOW_FIRSTJOB(String name) {
        Integer money = 20000;
        System.out.println(Thread.currentThread().getName() + ":" + name + "第一份工作月薪" + money + "啦");
        return money;
    }

    @Flow(value = "FLOW_SECONDJOB", retrieve = "age")
    public Integer FLOW_SECONDJOB(String name, Integer money) {
        money += 20000;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + name + "第二份工作月薪" + money + "啦");
        return money;
    }


}
