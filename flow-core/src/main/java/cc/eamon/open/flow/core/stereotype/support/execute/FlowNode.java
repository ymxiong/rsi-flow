package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: lucas
 * @date: 2020/7/12
 * @email: lucas@eamon.cc
 */
public class FlowNode implements FlowExecutable,Source,Target{

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
            if (!parameterClasses[i].equals(FlowContextHolder.get().getVariableTypeMap().get(parameterNames[i]))) {
                // TODO: 参数匹配
                System.out.println("参数不匹配");
            }
            args[i] = FlowContextHolder.get().getVariableMap().get(parameterNames[i]);
        }

        flowCode.execute(args);

        executeNext();
    }


    private void executeNext() {
        List<Target> targets = flowHolder.getRoute().route(this);
        //没有目标了
        if(targets.isEmpty())
            return;
        if (targets.size()<=1){
            executeNext(targets.get(0));
        }else {
            executeNext(targets);
        }
    }

    //线性顺序执行
    private void executeNext(Target target){
        FlowNode nextNode=new FlowNode(flowHolder.getFlowCodes().get(target.getName()),
                target.getId(),flowHolder);
        nextNode.execute();
    }

    //TODO:网关并行运行
    private void executeNext(List<Target> targets){
        for (Target target : targets) {
            FlowNode nextNode=new FlowNode(flowHolder.getFlowCodes().get(target.getName()),
                    target.getId(),flowHolder);
            nextNode.execute();
        }
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return flowCode.getFlowName();
    }

    @Override
    public String getType() {
        return null;
    }
}
