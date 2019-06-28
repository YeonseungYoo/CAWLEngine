package CAWLEngine.WorkflowPool;

public class From {
	private String expression;
	private String variable;
	private String part;
	
	public From() {
		expression = "";
		variable = "";
		part = "";
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
	
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public void setPart(String part) {
		this.part = part;
	}
	
	public String getPart() {
		return part;
	}
}
