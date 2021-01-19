package cc.eamon.open.flow.core.check;

import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.FlowItem;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 参数检查入口
 */
public class CheckEntrance {

    private Stack<RouteConfig> routeConfigs;

    private List<FlowItem> flowPaths;
    //TODO:首先需要读取到所需要的一系列配置文件
    //TODO:然后针对一个主的进行分析
    //TODO:分析过程
    //TODO:运用栈遍历得到所有执行路径
    //TODO:针对每一条路径执行校验，查看是否符合要求

    public CheckEntrance() {
        this.routeConfigs = new Stack<>();
        this.flowPaths= new ArrayList<>();
    }

    /**
     * 参数校验入口
     * @param path 配置文件根路径
     * @param flowName 待检验的主流程的名字
     * @return
     */
    private CheckResult check(String path,String flowName){
        //TODO:Step1.读取所有配置文件进行前期准备

        //TODO:Step2.对目标流程进行校验

        CheckResult checkResult=new CheckResult();

        return checkResult;
    }

    /**
     * Step2.1 深度优先遍历得到所有路径
     * @param config
     * @param checkResult
     * @return
     */
    private CheckResult getPaths(FlowConfig config,CheckResult checkResult){

        //TODO:深度优先遍历得到所有路径
        return checkResult;
    }

    /**
     * Step2.2 具体检查某条路径
     * @param checkResult
     * @return
     */
    private CheckResult checkOnePath(FlowItem flowItem,CheckResult checkResult){
        //TODO:检查某条路径
        //TODO:如果不符合要求的话，那么就要提示出错位置以及出错信息
        //TODO:每检查完一条路径之后需要清空上下文为下一次检查做准备
        return checkResult;
    }





}
