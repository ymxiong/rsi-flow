package cc.eamon.open.flow.model.enums;

import cc.eamon.open.flow.model.parse.LongDateParser;
import cc.eamon.open.flow.model.parse.Parser;

public enum ParseEnums {

    /**
     * 基础数据类型自动转换
     */
    LONG_DATE(BasicTypeEnums.LONG, BasicTypeEnums.DATE, new LongDateParser()),

    ;

    private final BasicTypeEnums from;

    private final BasicTypeEnums to;

    private final Parser parser;

    ParseEnums(BasicTypeEnums from, BasicTypeEnums to, Parser parser) {
        this.from = from;
        this.to = to;
        this.parser = parser;
    }

    public BasicTypeEnums getFrom() {
        return from;
    }

    public BasicTypeEnums getTo() {
        return to;
    }

    public Parser getParser() {
        return parser;
    }

    public static Object parse(BasicTypeEnums from, BasicTypeEnums to, Object param) {
        for (ParseEnums value : values()) {
            if (value.from == from && value.to == to) {
                return value.getParser().parse(param);
            }
        }
        return param;
    }

    public static Object parse(Class<?> from, Class<?> to, Object param) {
        return parse(BasicTypeEnums.getTypeByClass(from), BasicTypeEnums.getTypeByClass(to), param);
    }
}

