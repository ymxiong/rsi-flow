package cc.eamon.open.flow.route.strategy.targets;

import cc.eamon.open.flow.route.stereotype.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum TargetRouteStrategyEnums {


    NORMALSTRATEGY("normal", NormalTargetStrategy.class),

    CLOSETARGETSTRATEGY("close", CloseTargetRouteStrategy.class);

    private String type;

    private Class<? extends TargetRouteStrategy> strategy;

    TargetRouteStrategyEnums(String type, Class<? extends TargetRouteStrategy> strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends TargetRouteStrategy> getStrategy() {
        return strategy;
    }

    public static TargetRouteStrategy targetStrategyInit(String type, Target target) {
        for (TargetRouteStrategyEnums value : TargetRouteStrategyEnums.values()) {
            if (value.getType().equals(type)) {
                try {
                    Class[] paramTypes = {Target.class};
                    Object[] params = {target};
                    Constructor constructor = value.getStrategy().getConstructor(paramTypes);
                    TargetRouteStrategy targetRouteStrategy = (TargetRouteStrategy) constructor.newInstance(params);
                    return targetRouteStrategy;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
