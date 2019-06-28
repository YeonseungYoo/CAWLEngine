package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Flow {
	private String name;
	private String documentation;
	
	private ArrayList<Message> messageList;
	private ArrayList<Variable> variableList;
	private Source source;
	private ArrayList<NodeC> nodeList;
	private Sink sink;
	private ArrayList<Link> linkList;
	
	public Flow() {
		name = "";
		documentation = "";
		
		messageList = new ArrayList<Message>();
		variableList = new ArrayList<Variable>();
		source = new Source();
		nodeList = new ArrayList<NodeC>();
		sink = new Sink();
		linkList = new ArrayList<Link>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setMessage(Message message) {
		messageList.add(message);
	}
	
	public Message getMessage() {
		int index = messageList.size() - 1;
		return messageList.get(index);
	}
	
	public ArrayList<Message> getMessageList() {
		return messageList;
	}
	
	public void setVariable(Variable variable) {
		variableList.add(variable);
	}
	
	public Variable getVariable() {
		int index = variableList.size() - 1;
		return variableList.get(index);
	}
	
	public ArrayList<Variable> getVariableList() {
		return variableList;
	}
	
	public void setSource(Source source) {
		this.source = source;
	}
	
	public Source getSource() {
		return source;
	}
	
	public void setNode(NodeC node) {
		nodeList.add(node);
	}
	
	public NodeC getNode() {
		int index = nodeList.size() - 1;
		return nodeList.get(index);
	}
	
	public ArrayList<NodeC> getNodeList() {
		return nodeList;
	}
	
	public void setSink(Sink sink) {
		this.sink = sink;
	}
	
	public Sink getSink() {
		return sink;
	}
	
	public void setLink(Link link) {
		linkList.add(link);
	}
	
	public Link getLink() {
		int index = linkList.size() - 1;
		return linkList.get(index);
	}
	
	public ArrayList<Link> getLinkList() {
		return linkList;
	}
}
