package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Activator {
	private String name;
	private String documentation;
	
	private ArrayList<Message> messageList;
	private ArrayList<Variable> variableList;
	private Activate activate;
	private Condition condition;
	
	public Activator() {
		name = "";
		documentation = "";
		
		messageList = new ArrayList<Message>();
		variableList = new ArrayList<Variable>();
		activate = new Activate();
		condition = new Condition();
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
	
	public void setActivate(Activate activate) {
		this.activate = activate;
	}
	
	public Activate getActivate() {
		return activate;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition() {
		return condition;
	}
}
