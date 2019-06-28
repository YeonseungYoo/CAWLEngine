package CAWLEngine.WorkflowPool;

public class Sink {
	private String name;
	private String documentation;
	
	public Sink() {
		name = "";
		documentation = "";
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
}
