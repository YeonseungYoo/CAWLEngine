package CAWLEngine.WorkflowPool;

public class Activate {
	private String flow;
	private String documentation;
	
	public Activate() {
		flow = "";
		documentation = "";
	}
	
	public void setFlow(String flow) {
		this.flow = flow;
	}
	
	public String getFlow() {
		return flow;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
