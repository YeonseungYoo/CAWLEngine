package CAWLEngine.TaskManager;

import java.util.ArrayList;

import CAWLEngine.Monitor.CAWLState;
import CAWLEngine.Monitor.CAWLTime;
import CAWLEngine.WorkflowPool.CAWL;
import CAWLEngine.WorkflowPool.WorkflowPool;

public class TaskManager {
	private CAWL cawl;
	private ArrayList<Thread> threadList;
	
	public TaskManager() {
		cawl = new CAWL();
		threadList = new ArrayList<Thread>();
	}
	
	public void manageStart(WorkflowPool workflowPool, CAWLState cawlState, CAWLTime cawlTime) {
		cawl = workflowPool.getCAWL();
		
		Task task = new Task(cawl, cawlState, cawlTime);
		Thread thread = new Thread(task);
		
		threadList.add(thread);
		
		thread.start();
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + cawl.getName() + " workflow running...");
	}
	
	public void manageStop(CAWLState cawlState, CAWLTime cawlTime) {
		for(int index = 0; index < threadList.size(); index++) {
			threadList.get(index).interrupt();
		}
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + " All workflow finish...");
	}
}
