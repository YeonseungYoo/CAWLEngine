package CAWLEngine.WorkflowPool;

public class Invoke {
	private String operation;
	private String subflow;
	private String documentation;
	
	public Invoke() {
		operation = "";
		subflow = "";
		documentation = "";
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setSubflow(String subflow) {
		this.subflow = subflow;
	}
	
	public String getSubflow() {
		return subflow;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
