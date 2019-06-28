package CAWLEngine.Monitor;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class CAWLState {
	private ArrayList<CAWLSystemLog> systemLogs;
	private ArrayList<CAWLNode> nodes;
	private CAWLSystemLog systemLog;
	private CAWLNode node;
	private JSONObject jsonObj;
	
	public CAWLState() {
		systemLogs = new ArrayList<CAWLSystemLog>();
		nodes = new ArrayList<CAWLNode>();
	}
	
	public synchronized String systemLogAccess(String tag, String value) {
		if(tag.equals("set")) {
			systemLog = new CAWLSystemLog();
			systemLog.setSystemLog(value);
			systemLogs.add(systemLog);
			return "";
		} else {
			String temp = "";
			
			for(int i = 0; i < systemLogs.size(); i++) {
				temp += systemLogs.get(i).getSystemLog() + ",";
			}
			
			if(!temp.equals("")) {
				temp = temp.substring(0, temp.length() - 1);
				systemLogs = new ArrayList<CAWLSystemLog>();
			}
			return temp;
		}
	}
	
	public synchronized String cawlNodesAccess(String tag, String value) {
		if(tag.equals("set")) {
			node = new CAWLNode();
			node.setNode(value);
			nodes.add(node);
			return "";
		} else if(tag.equals("delete")) {
			for(int i = 0; i < nodes.size(); i++) {
				if(nodes.get(i).getNode().equals(value)) {
					nodes.remove(i);
					break;
				}
			}
			return "";
		} else {
			String temp = "";
			
			for(int i = 0; i < nodes.size(); i++) {
				temp += nodes.get(i).getNode() + ",";
			}
			
			if(!temp.equals("")) {
				temp = temp.substring(0, temp.length() - 1);
			}
			return temp;
		}
	}
	
	public String check() {
		jsonObj = new JSONObject();
		jsonObj.put("key", "cawlState");
		jsonObj.put("systemLog", systemLogAccess("get",""));
		jsonObj.put("cawlNodes", cawlNodesAccess("get",""));
		
		return jsonObj.toString();
	}
}
