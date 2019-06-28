package CAWLEngine.WorkflowPool;

import java.util.ArrayList;

public class WorkflowPool {
	private ArrayList<CAWL> cawlList;
	
	public WorkflowPool() {
		cawlList = new ArrayList<CAWL>();
	}
	
	public void setCAWL(CAWL cawl) {
		cawlList.add(cawl);
	}
	
	public CAWL getCAWL() {
		int index = cawlList.size() - 1;
		return cawlList.get(index);
	}
	
	public ArrayList<CAWL> getCawlList() {
		return cawlList;
	}
}
