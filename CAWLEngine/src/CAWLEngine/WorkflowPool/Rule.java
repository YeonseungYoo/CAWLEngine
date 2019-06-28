package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Rule {
	private String name;
	private String expression;
	private String documentation;
	
	private ArrayList<Constraint> constraintList;
	
	public Rule() {
		name = "";
		expression = "";
		documentation = "";
		
		constraintList = new ArrayList<Constraint>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
	
	public void setConstraint(Constraint constraint) {
		constraintList.add(constraint);
	}
	
	public Constraint getConstraint() {
		int index = constraintList.size() - 1;
		return constraintList.get(index);
	}
	
	public ArrayList<Constraint> getConstraintList() {
		return constraintList;
	}
}
