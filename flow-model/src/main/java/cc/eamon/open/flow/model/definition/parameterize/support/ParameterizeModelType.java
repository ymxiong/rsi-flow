package cc.eamon.open.flow.model.definition.parameterize.support;

import cc.eamon.open.flow.model.definition.parameterize.ModelType;
import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/9/21 6:10 下午
 **/
public class ParameterizeModelType implements ModelType {

    private List<ModelType> subModelTypes = new ArrayList<>();

    private String typeName;

    private String mainTypeName;

    private String subTypeName;

    private Queue<String> queue = new LinkedList<>();

    public ParameterizeModelType(String typeName, String mainTypeName) {
        this.mainTypeName = mainTypeName;
        this.typeName = typeName;
        this.subTypeName = typeName.substring(typeName.indexOf("(") + 1, typeName.lastIndexOf(")"));
        char[] charArray = subTypeName.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ((c + "").matches("\\w") || (c + "").equals(".")) queue.offer(c + "");
            else if (c == '(') {
                StringBuilder sb = new StringBuilder();
                while (queue.peek() != null) {
                    sb.append(queue.poll());
                }
                int j = 0;
                for (j = i + 1; j < charArray.length; j++) {
                    if (charArray[j] == ')') break;
                }
                String subMainTypeName = sb.toString();
                subModelTypes.add(new ParameterizeModelType(sb.append(subTypeName, i, j + 1).toString(), subMainTypeName));
            } else if (c == '$') {
                StringBuilder sb = new StringBuilder();
                while (queue.peek() != null) {
                    sb.append(queue.poll());
                }
                subModelTypes.add(new CustomModelType(sb.toString()));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (queue.peek() != null) {
            sb.append(queue.poll());
        }
        if (sb.length() > 0) {
            subModelTypes.add(new CustomModelType(sb.toString()));
        }
    }

    @Override
    public Type handle() {
        Type[] argTypes = new Type[subModelTypes.size()];
        for (int i = 0; i < subModelTypes.size(); ++i) {
            argTypes[i] = subModelTypes.get(i).handle();
        }
        return new ParameterizedTypeImpl(argTypes, null, BasicTypeEnums.getType(mainTypeName));
    }
}
