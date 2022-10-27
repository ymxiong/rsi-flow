package cc.eamon.open.flow.core.stereotype.support.enums;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 22:02:36
 */
public enum TypeEnums {

    VOID("void", void.class),
    BOOLEAN("boolean", boolean.class, false),
    BYTE("byte", byte.class, 0),
    SHORT("short", short.class, 0),
    INT("int", int.class, 0),
    LONG("long", long.class, 0),
    CHAR("char", char.class, 0),
    FLOAT("float", float.class, 0.0F),
    DOUBLE("double", double.class, 0.0),
    BOXED_VOID("Void", Void.class),
    BOXED_BOOLEAN("Boolean", Boolean.class, false),
    BOXED_BYTE("Byte", Byte.class, 0),
    BOXED_SHORT("Short", Short.class, 0),
    BOXED_INT("Integer", Integer.class, 0),
    BOXED_LONG("Long", Long.class, 0L),
    BOXED_CHAR("Character", Character.class),
    BOXED_FLOAT("Float", Float.class, 0.0F),
    BOXED_DOUBLE("Double", Double.class, 0.0),
    STRING("String", String.class),
    OBJECT("Object", Object.class),

    ;

    private String keyword;

    private Class<?> classType;

    private Object defaultValue = null;

    TypeEnums(String keyword, Class<?> classType) {
        this.keyword = keyword;
        this.classType = classType;
    }

    TypeEnums(String keyword, Class<?> classType, Object defaultValue) {
        this.keyword = keyword;
        this.classType = classType;
        this.defaultValue = defaultValue;
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
        return TypeEnums.OBJECT;
//        throw new FlowException("不支持的数据类型");
    }

    public static TypeEnums getTypeByClass(Class<?> classType) {
        for (TypeEnums value : TypeEnums.values()) {
            if (classType != null && classType == value.getClassType()) return value;
        }
        return TypeEnums.OBJECT;
//        throw new FlowException("不支持的数据类型");
    }

    public static Object getDefaultValueByClass(Class<?> classType) {
        for (TypeEnums value : TypeEnums.values()) {
            if (classType != null && classType == value.getClassType()) return value.defaultValue;
        }
        return null;
    }

    public static Object getDefaultValueByKeyword(String keyword) {
        for (TypeEnums value : TypeEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value.defaultValue;
        }
        return null;
    }

    public String getKeyword() {
        return keyword;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
