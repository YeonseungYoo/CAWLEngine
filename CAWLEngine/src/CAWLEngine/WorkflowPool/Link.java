package CAWLEngine.WorkflowPool;

public class Link {
	private String from;
	private String to;
	private String documentation;
	
	public Link() {
		from = "";
		to = "";
		documentation = "";
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
