package cc.eamon.open.flow.config.flow;

import java.io.Serializable;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-10-27 17:54:54
 */
public class ImportConfig implements Serializable {

    // 标题
    private String title;

    private String name;

    private String group;

    private Boolean ifDefault = false;

    private Boolean ifType;

    public ImportConfig() {
    }

    public ImportConfig(String name, String group, Boolean ifDefault, Boolean ifType) {
        this.name = name;
        this.group = group;
        this.ifDefault = ifDefault;
        this.ifType = ifType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getIfDefault() {
        return ifDefault;
    }

    public void setIfDefault(Boolean ifDefault) {
        this.ifDefault = ifDefault;
    }

    public Boolean getIfType() {
        return ifType;
    }

    public void setIfType(Boolean ifType) {
        this.ifType = ifType;
    }
}
