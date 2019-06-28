package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class Message {
	private String name;
	private String documentation;
	
	private ArrayList<Part> partList;
	
	public Message() {
		name = "";
		documentation = "";
		
		partList = new ArrayList<Part>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setPart(Part part) {
		partList.add(part);
	}
	
	public Part getPart() {
		int index = partList.size() - 1;
		return partList.get(index);
	}
	
	public ArrayList<Part> getPartList() {
		return partList;
	}
}
