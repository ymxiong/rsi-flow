package cc.eamon.open.flow.model.enums;


import cc.eamon.open.flow.model.stereotype.load.FlowModelLoader;
import cc.eamon.open.flow.model.stereotype.support.load.FileSystemLoader;
import cc.eamon.open.flow.model.stereotype.support.load.JarLoader;
import cc.eamon.open.flow.model.stereotype.support.load.ResourceLoader;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:13:49
 */
public enum ModelLoaderEnums {

    /**
     *
     */
    CLASSPATH_SCANNER("resource loader", new ResourceLoader()),

    /**
     *
     */
    FILESYSTEM_SCANNER("file system loader", new FileSystemLoader()),

    /**
     *
     */
    JAR_SCANNER("jar loader", new JarLoader());

    ModelLoaderEnums(String name, FlowModelLoader loader) {
        this.name = name;
        this.loader = loader;
    }

    /**
     *
     */
    private String name;
    /**
     *
     */
    private FlowModelLoader loader;

    public String getName() {
        return name;
    }

    public FlowModelLoader getLoader() {
        return loader;
    }
}
