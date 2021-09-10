package cc.eamon.open.flow.route.strategy.source;

import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.SourceRouteStrategy;
import cc.eamon.open.flow.route.stereotype.Source;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SourceRouteStrategyEnums {


    ASYNCALLSTRATEGY("all", AsyncAllStrategy.class),

    ASYNCANYSTRATEGY("any", AsyncAnyStrategy.class),

    ASYNCCLOSESTRATEGY("close", AsyncCloseStrategy.class),

    ASYNCVOIDSTRATEGY("async", AsyncVoidStrategy.class),

    NORMALRESULTSTRATEGY("normal", NormalSourceStrategy.class);

    private String type;

    private Class<? extends SourceRouteStrategy> strategy;

    SourceRouteStrategyEnums(String type, Class<? extends SourceRouteStrategy> strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends SourceRouteStrategy> getStrategy() {
        return strategy;
    }

    public void setStrategy(Class<? extends SourceRouteStrategy> strategy) {
        this.strategy = strategy;
    }


    public static SourceRouteStrategy routeStrategyInit(String type, Source source) {
        return routeStrategyInit(type, source, null);
    }

    public static SourceRouteStrategy routeStrategyInit(String type, Source source, Route route) {
        for (SourceRouteStrategyEnums value : SourceRouteStrategyEnums.values()) {
            if (value.getType().equals(type)) {
                try {
                    Class[] paramTypes = {Source.class};
                    Object[] params = {source};
                    Constructor constructor = value.getStrategy().getConstructor(paramTypes);
                    SourceRouteStrategy routeStrategy = (SourceRouteStrategy) constructor.newInstance(params);
                    if (route != null) routeStrategy.addRoute(route);
                    return routeStrategy;
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
