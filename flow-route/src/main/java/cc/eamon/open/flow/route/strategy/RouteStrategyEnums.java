package cc.eamon.open.flow.route.strategy;

import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.RouteStrategy;
import cc.eamon.open.flow.route.stereotype.Source;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum RouteStrategyEnums {


    ASYNCALLSTRATEGY("all", AsyncAllStrategy.class),

    ASYNCANYSTRATEGY("any", AsyncAnyStrategy.class),

    ASYNCCLOSESTRATEGY("close", AsyncCloseStrategy.class),

    ASYNCVOIDSTRATEGY("async", AsyncVoidStrategy.class),

    NORMALRESULTSTRATEGY("normal", NormalResultStrategy.class);

    private String type;

    private Class<? extends RouteStrategy> strategy;

    RouteStrategyEnums(String type, Class<? extends RouteStrategy> strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends RouteStrategy> getStrategy() {
        return strategy;
    }

    public void setStrategy(Class<? extends RouteStrategy> strategy) {
        this.strategy = strategy;
    }


    public static RouteStrategy routeStrategyInit(String type, Source source) {
        return routeStrategyInit(type, source, null);
    }

    public static RouteStrategy routeStrategyInit(String type, Source source, Route route) {
        for (RouteStrategyEnums value : RouteStrategyEnums.values()) {
            if (value.getType().equals(type)) {
                try {
                    Class[] paramTypes = {Source.class};
                    Object[] params = {source};
                    Constructor constructor = value.getStrategy().getConstructor(paramTypes);
                    RouteStrategy routeStrategy = (RouteStrategy) constructor.newInstance(params);
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
