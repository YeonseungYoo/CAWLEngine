package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class NodeC {
	private String name;
	private String state;
	private String documentation;
	
	private ArrayList<Message> messageList;
	private ArrayList<Variable> variableList;
	private ArrayList<Wait> waitList;
	private Condition condition;
	private ArrayList<Invoke> invokeList;
	
	public NodeC() {
		name = "";
		state = "";
		documentation = "";
		
		messageList = new ArrayList<Message>();
		variableList = new ArrayList<Variable>();
		waitList = new ArrayList<Wait>();
		condition = new Condition();
		invokeList = new ArrayList<Invoke>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
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
	
	public void setWait(Wait wait) {
		waitList.add(wait);
	}
	
	public Wait getWait() {
		int index = waitList.size() - 1;
		return waitList.get(index);
	}
	
	public ArrayList<Wait> getWaitList() {
		return waitList;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public void setInvoke(Invoke invoke) {
		invokeList.add(invoke);
	}
	
	public Invoke getInvoke() {
		int index = invokeList.size() - 1;
		return invokeList.get(index);
	}
	
	public ArrayList<Invoke> getInvokeList() {
		return invokeList;
	}
}
