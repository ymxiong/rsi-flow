package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.element.FlowDetail;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author: lucas
 * @date: 2020/6/29
 * @email: lucas@eamon.cc
 */
public class DisposeContextBuilder {

    /**
     * 用于解析目标JSON文件封装信息到Dispose中
    * @param filePath
     * @throws IOException
     */
    public static void parseJson(String filePath) throws IOException {
        JSONObject object=null;
        InputStream inputStream = new FileInputStream(filePath);
        String text = IOUtils.toString(inputStream,"utf8");
        object = (JSONObject) JSON.parse(text);
        String flowsName = (String) object.get("name");
        DisposeContext.setFlowName(flowsName);
        FlowDetail start=new FlowDetail(flowsName);
        DisposeContext.setStartNode(start);
        JSONArray flows = (JSONArray) object.get("flows");
        Iterator<Object> it = flows.iterator();
        while (it.hasNext()) {
            // JSONArray中是很多个JSONObject对象
            JSONObject flow = (JSONObject) it.next();
            String flowName= (String) flow.get("name");
            DisposeContext.getNodeList().add(flowName);
            FlowDetail nextNode=new FlowDetail(flowName);
            DisposeContext.addFlow(nextNode);
        }
    }
}
