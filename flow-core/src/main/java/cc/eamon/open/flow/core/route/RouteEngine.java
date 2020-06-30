package cc.eamon.open.flow.core.route;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.entity.ProcessNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
 */
public class RouteEngine {

	/**
	  * 获取下一节点（可能有多个）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessNode> getNextNode(String id) throws Exception {
		List<ProcessNode> processNodes = FlowContextHolder.get().getProcessNodes();
		// 寻找当前节点
		ProcessNode curreNode = getNodeById(id, processNodes);
		if(curreNode == null) {
			throw new Exception("当前路由里面没有" + id);
		}
		// 寻找下一节点
		return getNextNode(curreNode, processNodes);
	}

	// 根据id获取路由节点
	private static ProcessNode getNodeById(String id, List<ProcessNode> processNodes) {
		for (ProcessNode processNode : processNodes) {
			if(processNode.getNodeId().equals(id)) {
				return processNode;
			}
		}
		return null;
	}
	
	//根据节点类型和条件判断下一节点id
	//TODO 网关的还没设计，只设计了线性的
	private static List<ProcessNode> getNextNode(ProcessNode node, List<ProcessNode> processNodes){
		String type = node.getType();
		List<ProcessNode> nodes = new ArrayList<>();
		String nextNodeId;
		ProcessNode nextNode;
		switch (type) {
			//开始节点
			case "start":
				//获取下一节点的id
				nextNodeId = node.getTargets().get(0).getTarget();
				nextNode = getNodeById(nextNodeId, processNodes);
				nodes.add(nextNode);
				break;
			//普通事件节点
			case "event":
				//获取下一节点的id
				nextNodeId = node.getTargets().get(0).getTarget();
				nextNode = getNodeById(nextNodeId, processNodes);
				nodes.add(nextNode);
				break;
			//空结束节点
			case "end":
				break;
			//终止节点
			case "terminate":
				break;
			//唯一网关
			case "exclusiveGateway":
//				List<Target> targets = node.getTargets();
//				for(Target target:targets) {
//					String condition = target.getCondition();//获取条件
//				}
				break;
			case "parallelGateway":
				break;
			case "inclusiveGateway":
				break;
			default:
				break;
		}
		return nodes;
	}
}
