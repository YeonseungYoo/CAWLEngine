package CAWLEngine.WorkflowPool;

public class Service {
	private String operation;
	private String documentation;
	
	public Service() {
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
