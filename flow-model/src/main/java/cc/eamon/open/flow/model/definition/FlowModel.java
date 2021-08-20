package cc.eamon.open.flow.model.definition;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowModel {

    private String id;

    private String simpleName;

    private String packageName;

    private Map<String, Class> basicFileds = new HashMap<>();

    private Map<String, FlowModel> complexFileds = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public Map<String, Class> getBasicFileds() {
        return basicFileds;
    }

    public Map<String, FlowModel> getComplexFileds() {
        return complexFileds;
    }

    public void addbasicFiled(String name, String reference) {
        Class clazz = null;
        try {
            clazz = Class.forName(reference);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        basicFileds.put(name, clazz);

    }

    public void addcomplexFiled(String name, FlowModel flowModel) {
        complexFileds.put(name, flowModel);
    }
}
