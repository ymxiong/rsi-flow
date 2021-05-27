package cc.eamon.open.flow.core.stereotype.support.enums;


import cc.eamon.open.flow.core.error.FlowException;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 22:02:36
 */
public enum TypeEnums {

    VOID("void", void.class),
    BOOLEAN("boolean", boolean.class),
    BYTE("byte", byte.class),
    SHORT("short", short.class),
    INT("int", int.class),
    LONG("long", long.class),
    CHAR("char", char.class),
    FLOAT("float", float.class),
    DOUBLE("double", double.class),
    BOXED_VOID("Void", Void.class),
    BOXED_BOOLEAN("Boolean", Boolean.class),
    BOXED_BYTE("Byte", Byte.class),
    BOXED_SHORT("Short", Short.class),
    BOXED_INT("Integer", Integer.class),
    BOXED_LONG("Long", Long.class),
    BOXED_CHAR("Character", Character.class),
    BOXED_FLOAT("Float", Float.class),
    BOXED_DOUBLE("Double", Double.class),
    STRING("String", String.class),
    OBJECT("Object", Object.class),

    ;

    private String keyword;

    private Class<?> classType;

    TypeEnums(String keyword, Class<?> classType) {
        this.keyword = keyword;
        this.classType = classType;
    }

    /**
     * 获取类型
     *
     * @param keyword 关键字
     * @return 枚举
     */
    public static TypeEnums getType(String keyword) {
        for (TypeEnums value : TypeEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        throw new FlowException("不支持的数据类型");
    }

    public String getKeyword() {
        return keyword;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
