package cc.eamon.open.flow.config.enums;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:56:34
 */
public enum FlowTypeEnums {

    START("start"),

    END("end"),

    EVENT("event"),

    FLOW("flow"),
    ;

    private String name;

    FlowTypeEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
