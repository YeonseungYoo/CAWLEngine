package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class ServiceProvider {
	private String name;
	private String location;
	private String documentation;
	
	private ArrayList<Service> serviceList;
	
	public ServiceProvider() {
		name = "";
		location = "";
		documentation = "";
		
		serviceList = new ArrayList<Service>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setService(Service service) {
		serviceList.add(service);
	}
	
	public Service getService() {
		int index = serviceList.size() - 1;
		return serviceList.get(index);
	}
	
	public ArrayList<Service> getServiceList() {
		return serviceList;
	}
}
