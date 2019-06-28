package CAWLEngine.WorkflowPool;

public class Wait {
	private String joinCondition;
	private String documentation;
	
	public Wait() {
		joinCondition = "";
		documentation = "";
	}
	
	public void setJoinCondition(String joinCondition) {
		this.joinCondition = joinCondition;
	}
	
	public String getJoinCondition() {
		return joinCondition;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
}
