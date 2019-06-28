package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class CAWL {
	private String name;
	private String namespace;
	private String version;
	private String documentation;
	
	private ArrayList<Message> messageList;
	private ArrayList<Variable> variableList;
	private BaseOntologies baseOntologies;
	private ArrayList<ServiceProvider> serviceProviderList;
	private Activator activator;
	private ArrayList<Flow> flowList;
	
	public CAWL() {
		name = "";
		namespace = "";
		version = "";
		documentation = "";
		
		messageList = new ArrayList<Message>();
		variableList = new ArrayList<Variable>();
		baseOntologies = new BaseOntologies();
		serviceProviderList = new ArrayList<ServiceProvider>();
		activator = new Activator();
		flowList = new ArrayList<Flow>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String getNamespace() {
		return namespace;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setMessage(Message message) {
		messageList.add(message);
	}
	
	public Message getMessage() {
		int index = messageList.size() - 1;
		return messageList.get(index);
	}
	
	public ArrayList<Message> getMessageList() {
		return messageList;
	}
	
	public void setVariable(Variable variable) {
		variableList.add(variable);
	}
	
	public Variable getVariable() {
		int index = variableList.size() - 1;
		return variableList.get(index);
	}
	
	public ArrayList<Variable> getVariableList() {
		return variableList;
	}
	
	public void setBaseOntologies(BaseOntologies baseOntologies) {
		this.baseOntologies = baseOntologies;
	}
	
	public BaseOntologies getBaseOntologies() {
		return baseOntologies;
	}
	
	public void setServiceProvider(ServiceProvider serviceProvider) {
		serviceProviderList.add(serviceProvider);
	}
	
	public ServiceProvider getServiceProvider() {
		int index = serviceProviderList.size() - 1;
		return serviceProviderList.get(index);
	}
	
	public ArrayList<ServiceProvider> getServiceProviderList() {
		return serviceProviderList;
	}
	
	public void setActivator(Activator activator) {
		this.activator = activator;
	}
	
	public Activator getActivator() {
		return activator;
	}
	
	public void setFlow(Flow flow) {
		flowList.add(flow);
	}
	
	public Flow getFlow() {
		int index = flowList.size() - 1;
		return flowList.get(index);
	}
	
	public ArrayList<Flow> getFlowList() {
		return flowList;
	}
}
