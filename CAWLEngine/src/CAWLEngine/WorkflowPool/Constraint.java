package CAWLEngine.WorkflowPool;

public class Constraint {
	private String name;
	private String documentation;
	
	private String subject;
	private String subjectType;
	private String verb;
	private String object;
	private String objectType;
	
	public Constraint() {
		name = "";
		documentation = "";
		
		subject = "";
		subjectType = "";
		verb = "";
		object = "";
		objectType = "";
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
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	public String getSubjectType() {
		return subjectType;
	}
	
	public void setVerb(String verb) {
		this.verb = verb;
	}
	
	public String getVerb() {
		return verb;
	}
	
	public void setObject(String object) {
		this.object = object;
	}
	
	public String getObject() {
		return object;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	public String getObjectType() {
		return objectType;
	}
}
