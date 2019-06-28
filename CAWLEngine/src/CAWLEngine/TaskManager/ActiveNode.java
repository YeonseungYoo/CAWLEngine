package CAWLEngine.TaskManager;

public class ActiveNode {
	private String nodeName;
	
	public synchronized void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public synchronized String getNodeName() {
		return nodeName;
	}
}
