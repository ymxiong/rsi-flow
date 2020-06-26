package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.FlowContextHolder;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    public static void init(){
        FlowContextHolder.init();
    }

    public static void clear(){
        FlowContextHolder.clear();
    }

    public static FlowEngine instance(){
        return new FlowEngine();
    }

    private FlowEngine() {}

    public FlowEngine run(String flowName){
        return this;
    }

    public FlowEngine register(String paramName, Object paramValue){
        return this;
    }

    public FlowEngine release(Object... param){
        return this;
    }

    public Object retrieve(String paramName){
        return null;
    }

}
