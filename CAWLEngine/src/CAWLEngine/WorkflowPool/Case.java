package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Case {
	private String name;
	private String expression;
	private String documentation;
	
	private ArrayList<Event> eventList;
	
	public Case() {
		name = "";
		expression = "";
		documentation = "";
		
		eventList = new ArrayList<Event>();
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
	
	public void setEvent(Event event) {
		eventList.add(event);
	}
	
	public Event getEvent() {
		int index = eventList.size() - 1;
		return eventList.get(index);
	}
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}
}
