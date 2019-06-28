package CAWLEngine.TaskManager;

import java.util.ArrayList;

import CAWLEngine.ContextComparator.ContextComparator;
import CAWLEngine.ContextComparator.ContextCondition;
import CAWLEngine.Controller.WorkflowControll;
import CAWLEngine.Monitor.CAWLState;
import CAWLEngine.Monitor.CAWLTime;
import CAWLEngine.WorkflowPool.Activator;
import CAWLEngine.WorkflowPool.CAWL;
import CAWLEngine.WorkflowPool.Constraint;
import CAWLEngine.WorkflowPool.Flow;
import CAWLEngine.WorkflowPool.Link;
import CAWLEngine.WorkflowPool.NodeC;
import CAWLEngine.WorkflowPool.Rule;
import CAWLEngine.WorkflowPool.ServiceProvider;

public class Task implements Runnable {
	private String mainFlowSink;
	private TaskCheck taskCheck;
	private CAWL cawl;
	private CAWLState cawlState;
	private CAWLTime cawlTime;
		
	private ContextComparator contextComparator;
	private ContextCondition contextCondition;
	
	private ArrayList<ServiceProvider> serviceProviderList;
	
	private Activator activator;
	private ArrayList<Rule> ruleList;
	private ArrayList<Constraint> constraintList;
	
	private ArrayList<Flow> flowList;
	private Flow mainFlow;
	private ArrayList<Link> mainFlowLinkList;
	private ArrayList<NodeC> mainFlowNodeList;
	private ActiveNode activeNode;
	private ArrayList<ActiveNode> activeNodeList;
	private ArrayList<ActiveNode> checkNodeList;
	
	private NodeThreadManager nodeThreadManager;
	
	private String ontologyInfo;
	
	public Task(CAWL cawl, CAWLState cawlState, CAWLTime cawlTime) {
		this.mainFlowSink = "";
		this.taskCheck = new TaskCheck();
		this.cawl = new CAWL();
		this.cawl = cawl;
		this.cawlState = new CAWLState();
		this.cawlState = cawlState;
		this.cawlTime = new CAWLTime();
		this.cawlTime = cawlTime;
		
		contextComparator = new ContextComparator();
		contextCondition = new ContextCondition();
		
		serviceProviderList = new ArrayList<ServiceProvider>();
		
		activator = new Activator();
		ruleList = new ArrayList<Rule>();
		constraintList = new ArrayList<Constraint>();
		
		flowList = new ArrayList<Flow>();
		mainFlow = new Flow();
		mainFlowLinkList = new ArrayList<Link>();
		mainFlowNodeList = new ArrayList<NodeC>();
		activeNode = new ActiveNode();
		activeNodeList = new ArrayList<ActiveNode>();
		checkNodeList = new ArrayList<ActiveNode>();
		
		nodeThreadManager = new NodeThreadManager();
		
		ontologyInfo = "";
	}
	
	public void run() {
		// Ontology Synchronize
		cawlState.systemLogAccess("set", cawlTime.getTime() + "Ontology synchronization running...");
		
		ontologyInfo = cawl.getBaseOntologies().getOntology().getLocation();
//		contextComparator.ontologySynchronize(ontologyInfo, );
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + "Ontology synchronization complete");
		
		// ServiceProvider Set
		serviceProviderList = cawl.getServiceProviderList();
		
		// Activator Run
		activator = cawl.getActivator();
		cawlState.systemLogAccess("set", cawlTime.getTime() + "[ Activator : " + activator.getName() + " start ]");
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + activator.getName() + " condition checking...");
		
		ruleList = activator.getCondition().getContext().getRuleList();
		for(int i = 0; i < ruleList.size(); i++) {
			constraintList = ruleList.get(i).getConstraintList();
			
			for(int j = 0; j < constraintList.size(); j++) {
				String subject = constraintList.get(j).getSubject();
				String verb = constraintList.get(j).getVerb();
				String object = constraintList.get(j).getObject();
				
				String jsonContext = contextCondition.getContextCondition(subject, verb, object);
//				contextComparator.contextCheck(ontologyInfo, jsonContext);
			}
		}
		
		String activatorMainFlow = activator.getActivate().getFlow();
		cawlState.systemLogAccess("set", cawlTime.getTime() + "[ Activator : " + activator.getName() + " end ]");
		
		// Workflow Run
		flowList = cawl.getFlowList();
		for(int i = 0; i < flowList.size(); i++) {
			if(activatorMainFlow.equals(flowList.get(i).getName())) {
				mainFlow = flowList.get(i);
				break;
			}
		}
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + mainFlow.getName() + " running...");
		mainFlowLinkList = mainFlow.getLinkList();
		
		String mainFlowSource = mainFlow.getSource().getName();
		String mainFlowFirstNode = "";
		for(int i = 0; i < mainFlowLinkList.size(); i++) {
			if(mainFlowSource.equals(mainFlowLinkList.get(i).getFrom())) {
				mainFlowFirstNode = mainFlowLinkList.get(i).getTo();
			}
		}
		
		mainFlowNodeList = mainFlow.getNodeList();
		
		activeNode.setNodeName(mainFlowFirstNode);
		activeNodeList.add(activeNode);
		
		mainFlowSink = mainFlow.getSink().getName();
		
		try {
			while(true) {
				if(WorkflowControll.getTaskControll()) {
					// Node Execute
					for(int i = 0; i < mainFlowNodeList.size(); i++) {
						for(int j = 0; j < activeNodeList.size(); j++) {
							if(mainFlowNodeList.get(i).getName().equals(activeNodeList.get(j).getNodeName())) {
								mainFlowNodeList.get(i).setState("Activate");
								nodeThreadManager.manage(mainFlowSink, taskCheck, ontologyInfo, mainFlowNodeList.get(i), flowList, serviceProviderList, nodeThreadManager, cawlState, cawlTime);
							}
						}
					}
				
					// Node Execute Check
					for(int i = 0; i < activeNodeList.size(); i++) {
						for(int j = 0; j < mainFlowNodeList.size(); j++) {
							if(activeNodeList.get(i).getNodeName().equals(mainFlowNodeList.get(j).getName())) {
								
								while(true) {
									if(mainFlowNodeList.get(j).getState().equals("deActivate")) {
										break;
									}
									
									if(taskCheck.getTaskCheck()) {
										break;
									}
									
									Thread.sleep(1000);
								}
								
							}
						}
					}
					
					// Workflow Link
					checkNodeList.clear();
					for(int i = 0; i < activeNodeList.size(); i++) {
						String temp = activeNodeList.get(i).getNodeName();
						activeNode = new ActiveNode();
						activeNode.setNodeName(temp);
						checkNodeList.add(activeNode);
					}
					
					int count = 0;
					activeNodeList.clear();
					for(int i = 0; i < mainFlowLinkList.size(); i++) {
						for(int j = 0; j < checkNodeList.size(); j++) {
							
							if(mainFlowLinkList.get(i).getFrom().equals(checkNodeList.get(j).getNodeName())) {
								String nextCheck = mainFlowLinkList.get(i).getTo();
								
								if(count == 0) {
									activeNode = new ActiveNode();
									activeNode.setNodeName(nextCheck);
									activeNodeList.add(activeNode);
									count++;
								} else {
									for(int k = 0; k < activeNodeList.size(); k++) {
										
										if(!nextCheck.equals(activeNodeList.get(k).getNodeName())) {
											activeNode = new ActiveNode();
											activeNode.setNodeName(nextCheck);
											activeNodeList.add(activeNode);
										}
										
									}
								}
								
							}
							
						}
					}
					
					boolean toggle = false;
					
					for(int j = 0; j < activeNodeList.size(); j++) {
						if(activeNodeList.get(j).getNodeName().equals(mainFlowSink)) {
							toggle = true;
							break;
						}
					}
					
					if(toggle == true) {
						break;
					}
				}
				
				if(!WorkflowControll.getTaskControll()) {
					cawlState.systemLogAccess("set", cawlTime.getTime() + " All workflow stopping...");
				}
				
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cawlState.cawlNodesAccess("set", "empty");
		cawlState.systemLogAccess("set", cawlTime.getTime() + mainFlow.getName() + " complete");
	}
}
