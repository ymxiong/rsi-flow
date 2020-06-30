package cc.eamon.open.flow.core.route;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.element.FlowDetail;
import cc.eamon.open.flow.core.route.entity.ProcessNode;
import cc.eamon.open.flow.core.route.entity.Target;
import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
 */
public class RouteEngine {

	/**
	  * 根据json字符串初始化路由节点
	 * 
	 * @param json
	 * @return
	 */
	public List<ProcessNode> parseJson(String json) {
		JSONObject jsonObject = new JSONObject(json);
		List<ProcessNode> routeList = new ArrayList<>();
		// 获取route数组
		JSONArray routeArray = jsonObject.getJSONArray("route");
		for (int i = 0; i < routeArray.length(); i++) {
			ProcessNode node = new ProcessNode();
			// 获取节点类型 名字 默认目标
			node.setName(routeArray.getJSONObject(i).getString("name"));
			node.setType(routeArray.getJSONObject(i).getString("type"));
			node.setType(routeArray.getJSONObject(i).getString("id"));
			node.setDefaultTarget(routeArray.getJSONObject(i).getString("default"));
			// 获取条件和目标
			JSONArray targets = routeArray.getJSONObject(i).getJSONArray("targets");
			List<Target> targetList = new ArrayList<>();
			for (int j = 0; j < targets.length(); j++) {
				String condition = targets.getJSONObject(j).getString("condition");
				String target = targets.getJSONObject(j).getString("target");
				Target temp = new Target(condition, target);
				targetList.add(temp);
			}
			node.setTargets(targetList);
			routeList.add(node);
		}
		return routeList;
	}

	/**
	  * 获取下一节点（可能有多个）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<FlowDetail> getNextNode(String id) throws Exception {
		List<ProcessNode> processNodes = FlowContextHolder.get().getProcessNodes();
		// 寻找当前节点
		ProcessNode curreNode = getNodeById(id, processNodes);
		if(curreNode == null)
			throw new Exception("当前路由里面没有"+id);
		// 寻找下一节点
		return getNextNode(curreNode, processNodes);
	}

	// 根据id获取路由节点
	private ProcessNode getNodeById(String id, List<ProcessNode> processNodes) {
		for (ProcessNode processNode : processNodes) {
			if(processNode.getNodeId().equals(id))
				return processNode;
		}
		return null;
	}
	
	//根据节点类型和条件判断下一节点id
	private List<FlowDetail> getNextNode(ProcessNode node, List<ProcessNode> processNodes){
		String type = node.getType();
		List<FlowDetail> flowDetails = new ArrayList<>();
		String nextNodeId;
		ProcessNode nextNode;
		switch (type) {
			/*开始节点*/
			case "start":
				nextNodeId = node.getTargets().get(0).getTarget();//获取下一节点的id
				nextNode = getNodeById(nextNodeId, processNodes);
				flowDetails.add(new FlowDetail(nextNodeId,nextNode.getName()));
				break;
			/*普通事件节点*/
			case "event":
				nextNodeId = node.getTargets().get(0).getTarget();//获取下一节点的id
				nextNode = getNodeById(nextNodeId, processNodes);
				flowDetails.add(new FlowDetail(nextNodeId,nextNode.getName()));
				break;
			/*空结束节点*/
			case "end":
				break;
			/*终止节点*/
			case "terminate":
				break;
			/*唯一网关*/
			case "exclusiveGateway":
				Map<String, Object> env = getAllVariables();//获取所有变量
				List<Target> targets = node.getTargets();
				for(Target target:targets) {
					String condition = target.getCondition();//获取条件
					boolean result = (boolean) AviatorEvaluator.execute(condition, env);
					if(result) {
						//条件正确
						nextNode = getNodeById(target.getTarget(), processNodes);
						flowDetails.add(new FlowDetail(target.getTarget(),nextNode.getName()));
						break;
					}
				}
				break;
			/*并行网关*/
			case "parallelGateway":
				List<Target> _targets = node.getTargets();
				for(Target target:_targets) {
					nextNode = getNodeById(target.getTarget(), processNodes);
					flowDetails.add(new FlowDetail(target.getTarget(),nextNode.getName()));
				}
				break;
			/*多路网关*/
			case "inclusiveGateway":
				Map<String, Object> _env = getAllVariables();//获取所有变量
				List<Target> targets2 = node.getTargets();
				boolean allFalse = true;//是否全部错误
				for(Target target:targets2) {
					String condition = target.getCondition();//获取条件
					boolean result = (boolean) AviatorEvaluator.execute(condition, _env);
					if(result) {
						//条件正确
						allFalse = false;
						nextNode = getNodeById(target.getTarget(), processNodes);
						flowDetails.add(new FlowDetail(target.getTarget(),nextNode.getName()));
					}
				}
				if(allFalse) {
					nextNode = getNodeById(node.getDefaultTarget(), processNodes);
					flowDetails.add(new FlowDetail(node.getDefaultTarget(),nextNode.getName()));
				}		
				break;
			default:
				break;
		}
		return flowDetails;
		
	}
	
	
	//将当前上下文的所有变量都存入Map中
	private Map<String, Object> getAllVariables(){
		return FlowContextHolder.get().getVariableMap();
	}
}
