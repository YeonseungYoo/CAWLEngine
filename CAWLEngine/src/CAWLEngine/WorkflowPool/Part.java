package CAWLEngine.WorkflowPool;

public class Part {
	private String name;
	private String type;
	private String element;
	private String documentation;
	
	public Part() {
		name = "";
		type = "";
		element = "";
		documentation = "";
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
	
	public void setElement(String element) {
		this.element = element;
	}
	
	public String getElement() {
		return element;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
