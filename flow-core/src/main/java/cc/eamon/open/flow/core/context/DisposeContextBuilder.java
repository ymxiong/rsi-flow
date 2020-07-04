package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.core.route.structure.FlowNode;
import cc.eamon.open.flow.core.route.structure.FlowTarget;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        FlowContextHolder.get().setFlowName(flowsName);
        JSONArray flows = (JSONArray) object.get("flows");
        Iterator<Object> it = flows.iterator();
        while (it.hasNext()) {
            // JSONArray中是很多个JSONObject对象
            JSONObject flow = (JSONObject) it.next();
            String flowName= (String) flow.get("name");
            DisposeContext.getNodeList().add(flowName);
        }


        List<FlowNode> routeList = new ArrayList<>();
        // 获取route数组
        JSONArray routeArray = object.getJSONArray("route");
        for (int i = 0; i < routeArray.size(); i++) {
            FlowNode node = new FlowNode();
            // 获取节点类型 名字 默认目标
            node.setName(routeArray.getJSONObject(i).getString("name"));
            node.setType(routeArray.getJSONObject(i).getString("type"));
            node.setNodeId(routeArray.getJSONObject(i).getString("id"));
//            node.setDefaultFlowTarget(routeArray.getJSONObject(i).getString("default"));
            // 获取条件和目标
            JSONArray targets = routeArray.getJSONObject(i).getJSONArray("targets");
            List<FlowTarget> targetList = new ArrayList<>();
            for (int j = 0; j < targets.size(); j++) {
                String condition = targets.getJSONObject(j).getString("condition");
                String target = targets.getJSONObject(j).getString("target");
                FlowTarget temp = new FlowTarget(condition, target);
                targetList.add(temp);
            }
//            node.setFlowTargets(targetList);
            if (node.getType().equals("start")){
                FlowContextHolder.get().setStartNode(node);
            }
            routeList.add(node);
        }
//        FlowContextHolder.get().setFlowNodes(routeList);
    }

}
