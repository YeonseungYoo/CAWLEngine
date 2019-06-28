package CAWLEngine.Monitor;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import CAWLEngine.WorkflowPool.CAWL;
import CAWLEngine.WorkflowPool.Flow;
import CAWLEngine.WorkflowPool.Invoke;
import CAWLEngine.WorkflowPool.NodeC;

public class CAWLSearch {
	private ArrayList<Flow> flowList;
	private ArrayList<NodeC> nodeList;
	private ArrayList<Invoke> invokeList;
	private JSONObject jsonObjKey;
	private JSONObject jsonObjNode;
	private JSONArray jsonArrNodes;
	private String invokes;
	
	public CAWLSearch() {
		flowList = new ArrayList<Flow>();
		nodeList = new ArrayList<NodeC>();
		invokeList = new ArrayList<Invoke>();
	}

	public String search(CAWL cawl) {
		jsonObjKey = new JSONObject();
		jsonArrNodes = new JSONArray();
		
		jsonObjKey.put("key", "cawlNodes");
		
		flowList = cawl.getFlowList();
		
		for(int i = 0; i < flowList.size(); i++) {
			nodeList = flowList.get(i).getNodeList();
			
			for(int j = 0; j < nodeList.size(); j++) {
				jsonObjNode = new JSONObject();
				
				jsonObjNode.put("node", nodeList.get(j).getName());
				jsonObjNode.put("documentation", nodeList.get(j).getDocumentation());
				jsonObjNode.put("condition", nodeList.get(j).getCondition().getExpression());
				
				invokes = "";
				invokeList = nodeList.get(j).getInvokeList();
				
				for(int k = 0; k < invokeList.size(); k++) {
					invokes += invokeList.get(k).getOperation() + ", ";
				}
				invokes = invokes.substring(0, invokes.length() - 2);
				
				jsonObjNode.put("service", invokes);
				jsonArrNodes.add(jsonObjNode);
			}
		}
		
		jsonObjKey.put("nodes", jsonArrNodes);
		return jsonObjKey.toString();
	}
}
