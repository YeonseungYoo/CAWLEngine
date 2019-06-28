package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Variable {
	private String name;
	private String type;
	private String documentation;
	
	private ArrayList<Initialize> initializeList;
	
	public Variable() {
		name = "";
		type = "";
		documentation = "";
		
		initializeList = new ArrayList<Initialize>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setInitialize(Initialize initialize) {
		initializeList.add(initialize);
	}
	
	public Initialize getInitialize() {
		int index = initializeList.size() - 1;
		return initializeList.get(index);
	}
	
	public ArrayList<Initialize> getInitializeList() {
		return initializeList;
	}
}
