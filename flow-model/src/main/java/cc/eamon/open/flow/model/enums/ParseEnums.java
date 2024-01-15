package cc.eamon.open.flow.model.enums;

import cc.eamon.open.flow.model.parse.LongDateParser;
import cc.eamon.open.flow.model.parse.Parser;

public enum ParseEnums {

    /**
     * 基础数据类型自动转换
     */
    LONG_DATE(TypeEnums.LONG, TypeEnums.DATE, new LongDateParser()),

    ;

    private final TypeEnums from;

    private final TypeEnums to;

    private final Parser parser;

    ParseEnums(TypeEnums from, TypeEnums to, Parser parser) {
        this.from = from;
        this.to = to;
        this.parser = parser;
    }

    public TypeEnums getFrom() {
        return from;
    }

    public TypeEnums getTo() {
        return to;
    }

    public Parser getParser() {
        return parser;
    }

    public static Object parse(TypeEnums from, TypeEnums to, Object param) {
        for (ParseEnums value : values()) {
            if (value.from == from && value.to == to) {
                return value.getParser().parse(param);
            }
        }
        return param;
    }

    public static Object parse(Class<?> from, Class<?> to, Object param) {
        return parse(TypeEnums.getTypeByClass(from), TypeEnums.getTypeByClass(to), param);
    }
}

