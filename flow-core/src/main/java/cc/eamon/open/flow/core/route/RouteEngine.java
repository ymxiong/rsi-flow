package cc.eamon.open.flow.core.route;


import cc.eamon.open.flow.core.route.entity.ProcessNode;
import cc.eamon.open.flow.core.route.entity.Target;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 17:13:16
 */
public class RouteEngine {


    //TODO 解析JSON字符串，获取流程
    public static List<ProcessNode> parseJson(String json) throws IOException {
        JSONObject jsonObject = new JSONObject(json);
        List<ProcessNode> routeList = new ArrayList<>();
        //获取route数组
        JSONArray routeArray = jsonObject.getJSONArray("route");
        for(int i = 0;i < routeArray.length();i++){
            ProcessNode node = new ProcessNode();
            //获取节点类型 名字 默认目标
            node.setName(routeArray.getJSONObject(i).getString("name"));
            node.setType(routeArray.getJSONObject(i).getString("type"));
            node.setDefaultTarget(routeArray.getJSONObject(i).getString("default"));
            //获取条件和目标
            JSONArray targets = routeArray.getJSONObject(i).getJSONArray("targets");
            List<Target> targetList = new ArrayList<>();
            for(int j = 0;j < targets.length();j++){
                 String condition = targets.getJSONObject(j).getString("condition");
                 String target = targets.getJSONObject(j).getString("target");
                 Target temp = new Target(condition,target);
                 targetList.add(temp);
            }
            node.setTargets(targetList);
            routeList.add(node);
        }
        return routeList;
    }
    //TODO 根据当前流程与上下文环境，确定下一节点
}
