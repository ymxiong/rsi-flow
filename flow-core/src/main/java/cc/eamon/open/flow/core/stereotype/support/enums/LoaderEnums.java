package cc.eamon.open.flow.core.stereotype.support.enums;

import cc.eamon.open.flow.core.stereotype.load.FlowLoader;
import cc.eamon.open.flow.core.stereotype.support.load.FileSystemLoader;
import cc.eamon.open.flow.core.stereotype.support.load.JarLoader;
import cc.eamon.open.flow.core.stereotype.support.load.ResourceLoader;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:13:49
 */
public enum LoaderEnums {

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

    LoaderEnums(String name, FlowLoader loader) {
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
    private FlowLoader loader;

    public String getName() {
        return name;
    }

    public FlowLoader getLoader() {
        return loader;
    }
}
