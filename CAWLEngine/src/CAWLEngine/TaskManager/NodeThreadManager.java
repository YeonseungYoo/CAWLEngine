package CAWLEngine.TaskManager;

import java.util.ArrayList;

import CAWLEngine.Monitor.CAWLState;
import CAWLEngine.Monitor.CAWLTime;
import CAWLEngine.WorkflowPool.Flow;
import CAWLEngine.WorkflowPool.NodeC;
import CAWLEngine.WorkflowPool.ServiceProvider;

public class NodeThreadManager {
	
	public synchronized void manage(String mainFlowSink, TaskCheck taskCheck, String ontologyInfo, NodeC node, ArrayList<Flow> flowList, ArrayList<ServiceProvider> serviceProvider, NodeThreadManager nodeThreadManager, CAWLState cawlState, CAWLTime cawlTime) {
		NodeThread nodeThread = new NodeThread(mainFlowSink, taskCheck, ontologyInfo, node, flowList, serviceProvider, nodeThreadManager, cawlState, cawlTime);
		Thread thread = new Thread(nodeThread);
		
		thread.start();
	}
}
