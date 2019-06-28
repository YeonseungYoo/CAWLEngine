package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class BaseOntologies {
	private String documentation;
	
	private ArrayList<Ontology> ontologyList;
	
	public BaseOntologies() {
		documentation = "";
		
		ontologyList = new ArrayList<Ontology>();
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setOntology(Ontology ontology) {
		ontologyList.add(ontology);
	}
	
	public Ontology getOntology() {
		int index = ontologyList.size() - 1;
		return ontologyList.get(index);
	}
	
	public ArrayList<Ontology> getOntologyList() {
		return ontologyList;
	}
}
