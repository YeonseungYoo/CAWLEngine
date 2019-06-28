package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Context {
	private String name;
	private String priority;
	private String documentation;
	
	private ArrayList<Rule> ruleList;
	
	public Context() {
		name = "";
		priority = "";
		documentation = "";
		
		ruleList = new ArrayList<Rule>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setRule(Rule rule) {
		ruleList.add(rule);
	}
	
	public Rule getRule() {
		int index = ruleList.size() - 1;
		return ruleList.get(index);
	}
	
	public ArrayList<Rule> getRuleList() {
		return ruleList;
	}
}
