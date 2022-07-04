package cc.eamon.open.flow.core.context;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2022-07-04 16:17:50
 */
public enum InitializerEnums {

    DEFAULT("DEFAULT", new Initializer()),

    ;

    private String id;

    private Initializer initializer;

    InitializerEnums(String id, Initializer initializer) {
        this.id = id;
        this.initializer = initializer;
    }

    public String getId() {
        return id;
    }

    public Initializer getInitializer() {
        return initializer;
    }
}
