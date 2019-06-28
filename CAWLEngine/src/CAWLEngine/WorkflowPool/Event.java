package CAWLEngine.WorkflowPool;

public class Event {
	private String operation;
	private String documentation;
	
	public Event() {
		operation = "";
		documentation = "";
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
