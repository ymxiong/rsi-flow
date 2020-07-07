package cc.eamon.open.flow.core.stereotype.support.load;

import cc.eamon.open.flow.core.stereotype.load.FlowLoader;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:13:49
 */
public enum FlowLoaderEnums {

    /**
     *
     */
    CLASSPATH_SCANNER("resource loader", new ResourceLoader()),

    /**
     *
     */
    FILESYSTEM_SCANNER("file system loader", new FileSystemLoader());

    FlowLoaderEnums(String name, FlowLoader loader) {
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
