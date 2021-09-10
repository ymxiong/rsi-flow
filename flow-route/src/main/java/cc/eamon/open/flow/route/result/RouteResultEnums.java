package cc.eamon.open.flow.route.result;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:23:08
 */
public enum RouteResultEnums {


    ASYNCALLRESULT("AsynAllResult", AsynAllResult.class),

    ASYNCANYRESULT("AsynAnyResult", AsyncAnyResult.class),

    ASYNCVOIDRESULT("AsynVoidResult", AsynVoidResult.class),

    NORMALRESULT("NormalResult", NormalResult.class);

    private String keyword;

    private Class<? extends RouteResult> routeResult;

    RouteResultEnums(String keyword, Class<? extends RouteResult> routeResult) {
        this.keyword = keyword;
        this.routeResult = routeResult;
    }


    public String getKeyword() {
        return keyword;
    }


    public static RouteResultEnums getType(String keyword) throws Exception {
        for (RouteResultEnums value : RouteResultEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        throw new Exception();
    }

}
