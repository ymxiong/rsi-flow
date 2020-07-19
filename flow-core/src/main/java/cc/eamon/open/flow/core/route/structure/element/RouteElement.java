package cc.eamon.open.flow.core.route.structure.element;

/**
 * 用于封装RouteConfig底层数据结构
 *
 * @author xsy
 */
public class RouteElement implements Source, Target {

    private String name;
    private String id;
    private String type;

    public RouteElement(String id) {
        this.id = id;
    }

    public RouteElement(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
