package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.core.error.FlowCallitSelfException;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowNode;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThreadPool;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/30 10:16
 */
public class BasicExecutable implements FlowExecutable, Source, Target {

    private static Logger logger = LoggerFactory.getLogger(BasicExecutable.class);
    /**
     * 流程节点的类型
     */
    protected String type;
    /**
     * 流程节点的名称
     */
    protected String name;

    /**
     * 流程节点的ID
     */
    protected String id;

    /**
     * 所属于的FlowHolder
     */
    protected FlowHolder parentFlowHolder;

    /**
     * 分支的最后一个执行节点
     */
    protected List<Source> branchSource;

    public BasicExecutable(String type, String name, String id, FlowHolder parentFlowHolder) {
        this.type = type;
        this.name = name;
        this.id = id;
        this.parentFlowHolder = parentFlowHolder;
    }

    public void setParentFlowHolder(FlowHolder parentFlowHolder) {
        this.parentFlowHolder = parentFlowHolder;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setBranchSource(List<Source> branchSource) {
        this.branchSource = branchSource;
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public void executeNext(List<Source> sources) throws Exception {
        List<Target> targets = parentFlowHolder.getRoute().route(sources);
        //没有目标了
        if (targets.isEmpty()) {
            return;
        }
        if (targets.size() <= 1) {
            executeNextNode(targets.get(0));
        } else {
            executeNextNodes(targets);
        }
    }

    @Override
    public void executeNext(Source source) throws Exception {
        //整体流程调用结束
        if (parentFlowHolder == null) return;
        List<Target> targets = parentFlowHolder.getRoute().route(source);
        //没有目标了
        if (targets.isEmpty()) {
            if (branchSource != null) {
                synchronized (branchSource) {
                    branchSource.add(source);
                }
            }
            return;
        }
        if (targets.size() <= 1) {
            executeNextNode(targets.get(0));
        } else {
            executeNextNodes(targets);
        }
    }

    @Override
    public void executeNextNode(Target target) throws Exception {
        FlowExecutable executable = getExecuteableByTarget(target, parentFlowHolder);
        executable.execute();
    }

    @Override
    public void executeNextNodes(List<Target> targets) throws Exception {
        CountDownLatch latch = new CountDownLatch(targets.size());
        FlowContextExtractor flowContextExtractor = new FlowContextExtractor();
        //保留了每个分支的执行节点
        List<Source> sources = new LinkedList<>();
        List<Future> futures = new LinkedList<>();
        //TODO:针对每一条业务路线都开启一个多线程来执行
        for (Target target : targets) {
            FlowExecutable executable = getExecuteableByTarget(target, parentFlowHolder);
            ((BasicExecutable) executable).setBranchSource(sources);
            FlowThread flowThread = new FlowThread(executable, flowContextExtractor, latch);
            FutureTask<FlowContextExtractor> futureTask = new FutureTask<FlowContextExtractor>(flowThread);
            Future future = FlowThreadPool.pool.submit(futureTask);
            futures.add(future);
        }
        //TODO:主线程等待所有子线程完成
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO:所有子线程变量放到父线程中
        FlowContextExtractor childContext = null;
        for (Future future : futures) {
            try {
                childContext = (FlowContextExtractor) future.get();
                FlowContextHolder.get().registerChildVariable(childContext);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //TODO:所有子线程执行完毕，网关闭合综合条件执行后续节点
        executeNext(sources);
    }


    /**
     * 获取下一个执行节点
     *
     * @param target
     * @return
     */
    public FlowExecutable getExecuteableByTarget(Target target, FlowHolder flowHolder) throws Exception {
        FlowExecutable executable;
        if (target.getType().equals("flow")) {
            executable = FlowDispose.getFlowHolderByName(target.getName());
            if (executable == null) {
                String errorInfo = "flow named " + target.getName() + " not exist.";
                logger.info(errorInfo);
                throw new FlowNotFoundException(errorInfo);
            }
            if (executable.equals(flowHolder)) {
                String errorInfo = "you can not invoke self flow named " + target.getName() + "in " + target.getName();
                //TODO:当前只是检查一层嵌套调用自身，不支持检查多层检查
                logger.info(errorInfo);
                throw new FlowCallitSelfException(errorInfo);
            }
            ((FlowHolder) executable).setParentFlowHolder(flowHolder);
        } else {
            FlowCode flowCode = flowHolder.getFlowCodes().get(target.getName());
            //TODO:从哪里找RouteConfig
            RouteConfig routeConfig = flowHolder.getRouteConfig(target.getName());
            if (flowCode == null) {
                String errorInfo = "flowCode named " + target.getName() + " not exist.";
                logger.info(errorInfo);
                throw new FlowCodeNotFoundException(errorInfo);
            }
            executable = new FlowNode(target.getType(), target.getName(), target.getId(),
                    flowHolder, flowCode, routeConfig);
        }
        return executable;
    }
}
