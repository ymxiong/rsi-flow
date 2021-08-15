package cc.eamon.open.flow.config.model;

import java.io.Serializable;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 13:21
 */
public class ModelFiledConfig implements Serializable {
    private static final long serialVersionUID = -1770569928514391987L;
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
