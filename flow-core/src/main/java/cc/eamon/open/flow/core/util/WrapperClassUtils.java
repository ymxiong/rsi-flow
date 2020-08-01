package cc.eamon.open.flow.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/8/1 15:45
 */
public class WrapperClassUtils {
    public static Map<Class, Class> frameMap = new HashMap<>();

    static {
        frameMap.put(boolean.class, Boolean.class);
        frameMap.put(byte.class, Byte.class);
        frameMap.put(char.class, Character.class);
        frameMap.put(short.class, Short.class);
        frameMap.put(int.class, Integer.class);
        frameMap.put(long.class, Long.class);
        frameMap.put(float.class, Float.class);
        frameMap.put(double.class, Double.class);
    }

    public static Class getWrapperClass(Class baseType) {
        Class clazz = frameMap.get(baseType);
        return clazz;
    }
}
