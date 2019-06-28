package CAWLEngine.WorkflowPool;

public class Initialize {
	private String part;
	
	private From from;
	
	public Initialize() {
		part = "";
		
		from = new From();
	}
	
	public void setPart(String part) {
		this.part = part;
	}
	
	public String getPart() {
		return part;
	}
	
	public void setFrom(From from) {
		this.from = from;
	}
	
	public From getFrom() {
		return from;
	}
}
