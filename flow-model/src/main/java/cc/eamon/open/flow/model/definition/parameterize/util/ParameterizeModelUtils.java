package cc.eamon.open.flow.model.definition.parameterize.util;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/9/21 7:52 下午
 **/
public class ParameterizeModelUtils {

    public static boolean isMatchParameterize(String type) {
        // 任意类的匹配:[\\w+[\\.\\w+]*]
        if (type.matches("\\w+\\([\\w+[\\.\\w+]*]+[\\$[\\w+[\\.\\w+]*]+]*\\)") || type.matches("[\\w+[\\.\\w+]*]+[\\$[\\w+[\\.\\w+]*]+]*"))
            return true;
        if (type.contains("(") && type.contains(")"))
            return isMatchParameterize(type.substring(type.indexOf("(") + 1, type.lastIndexOf(")")));
        return false;
    }
}
