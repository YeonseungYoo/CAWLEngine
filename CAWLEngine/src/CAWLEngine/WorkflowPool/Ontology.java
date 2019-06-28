package CAWLEngine.WorkflowPool;

public class Ontology {
	private String location;
	private String namespace;
	private String documentation;
	
	public Ontology() {
		location = "";
		namespace = "";
		documentation = "";
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String getNamespace() {
		return namespace;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
