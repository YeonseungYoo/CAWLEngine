package CAWLEngine.WorkflowPool;

public class Condition {
	private String expression;
	private String documentation;
	
	private Case casec;
	private Context context;
	
	public Condition() {
		expression = "";
		documentation = "";
		
		casec = new Case();
		context = new Context();
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setCase(Case casec) {
		this.casec = casec;
	}
	
	public Case getCase() {
		return casec;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public Context getContext() {
		return context;
	}
}
