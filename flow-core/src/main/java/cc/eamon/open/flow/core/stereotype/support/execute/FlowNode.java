package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThreadPool;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author: lucas
 * @date: 2020/7/12
 * @email: lucas@eamon.cc
 */
public class FlowNode implements FlowExecutable, Source, Target {

    private FlowCode flowCode;

    private String id;

    private FlowHolder flowHolder;



    public FlowNode(FlowCode flowCode, String id, FlowHolder flowHolder) {
        this.flowCode = flowCode;
        this.id = id;
        this.flowHolder = flowHolder;
    }

    @Override
    public void execute() {
        Method method = flowCode.getMethod();
        // 注入流程代码所需参数
        // 拿到参数名称以及类型
        Class<?>[] parameterClasses = method.getParameterTypes();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        // 注入参数
        assert parameterNames != null;
        int parameterNum = parameterNames.length;
        Object[] args = new Object[parameterNum];

        for (int i = 0; i < parameterNum; i++) {
            if (!parameterClasses[i].equals(FlowContextHolder.get().retrieveVariableType(parameterNames[i]))) {
                // TODO: 参数类型匹配
                throw new RuntimeException(parameterNames[i] + " does not match.");
            }
            Object obj = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
            if (obj == null) throw new RuntimeException(parameterNames[i] + " is missing.");
            args[i] = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
        }

        flowCode.execute(args);
        executeNext();
    }


    @Override
    public void executeNext() {
        List<Target> targets = flowHolder.getRoute().route(this);
        //没有目标了
        if (targets.isEmpty())
            return;
        if (targets.size() <= 1) {
            executeNext(targets.get(0));
        } else {
            executeNext(targets);
        }
    }


    //TODO:执行下一个节点
    private void executeNext(Target target) {
        //TODO:考虑嵌套
        if(target instanceof FlowHolder){
            FlowHolder flowHolder=(FlowHolder)(target);
            flowHolder.execute();
            executeNext();
        }
        else {
            FlowNode nextNode = new FlowNode(flowHolder.getFlowCodes().get(target.getName()),
                    target.getId(), flowHolder);
            nextNode.execute();
        }

    }

    //TODO:后续网关并行运行
    private void executeNext(List<Target> targets) {
        //网关结束之后返回执行单节点
        if (targets.size() <= 1) executeNext(targets.get(0));
        CountDownLatch latch = new CountDownLatch(targets.size());
        FlowContextExtractor flowContextExtractor = new FlowContextExtractor();
        //保留了每个分支的执行节点
        List<FlowNode> flowNodes = new LinkedList<>();
        List<Future> futures = new LinkedList<>();
        //TODO:针对每一条业务路线都开启一个多线程来执行
        for (Target target : targets) {
            FlowNode nextNode = new FlowNode(flowHolder.getFlowCodes().get(target.getName()),
                    target.getId(), flowHolder);
            flowNodes.add(nextNode);
            FlowThread flowThread = new FlowThread(flowHolder, flowContextExtractor, latch);
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
        //TODO:所有子线程执行完毕，网关闭合综合条件拿到下一个节点(怎么综合网关)
        List<Target> nextTargets = flowHolder.getRoute().route((Source) flowNodes);
        executeNext(nextTargets);

    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return flowCode.getFlowName();
    }
}
