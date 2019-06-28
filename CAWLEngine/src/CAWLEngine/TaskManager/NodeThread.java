package CAWLEngine.TaskManager;

import java.util.ArrayList;

import CAWLEngine.ContextComparator.ContextComparator;
import CAWLEngine.ContextComparator.ContextCondition;
import CAWLEngine.Controller.WorkflowControll;
import CAWLEngine.Monitor.CAWLState;
import CAWLEngine.Monitor.CAWLTime;
import CAWLEngine.ServiceProvider.ServiceAgent;
import CAWLEngine.WorkflowPool.Constraint;
import CAWLEngine.WorkflowPool.Flow;
import CAWLEngine.WorkflowPool.Invoke;
import CAWLEngine.WorkflowPool.Link;
import CAWLEngine.WorkflowPool.NodeC;
import CAWLEngine.WorkflowPool.Rule;
import CAWLEngine.WorkflowPool.ServiceProvider;

public class NodeThread implements Runnable {
	private String mainFlowSink;
	private TaskCheck taskCheck;
	private String ontologyInfo;
	private NodeC node;
	private ArrayList<Flow> flowList;
	private ArrayList<ServiceProvider> serviceProviderList;
	private ServiceProvider serviceProvider;
	private NodeThreadManager nodeThreadManager;
	private CAWLState cawlState;
	private CAWLTime cawlTime;
	private ArrayList<Rule> ruleList;
	private ArrayList<Constraint> constraintList;
	private ContextCondition contextCondition;
	
	private ContextComparator contextComparator;
	private ArrayList<Invoke> invokeList;
	private Invoke invoke;
	private Flow subFlow;
	private ArrayList<Link> subFlowLinkList;
	private ArrayList<NodeC> subFlowNodeList;
	private ActiveNode activeNode;
	private ArrayList<ActiveNode> activeNodeList;
	private ArrayList<ActiveNode> checkNodeList;
	
	private ServiceAgent serviceAgent;
	private String serviceProviderLocation;
	
	public NodeThread(String mainFlowSink, TaskCheck taskCheck, String ontologyInfo, NodeC node, ArrayList<Flow> flowList, ArrayList<ServiceProvider> serviceProviderList, NodeThreadManager nodeThreadManager, CAWLState cawlState, CAWLTime cawlTime) {
		this.mainFlowSink = mainFlowSink;
		this.taskCheck = new TaskCheck();
		this.taskCheck = taskCheck;
		this.ontologyInfo = ontologyInfo;
		this.node = new NodeC();
		this.node = node;
		this.flowList = new ArrayList<Flow>();
		this.flowList = flowList;
		this.serviceProviderList = new ArrayList<ServiceProvider>();
		this.serviceProviderList = serviceProviderList;
		serviceProvider = new ServiceProvider();
		this.nodeThreadManager = new NodeThreadManager();
		this.nodeThreadManager = nodeThreadManager;
		this.cawlState = new CAWLState();
		this.cawlState = cawlState;
		this.cawlTime = new CAWLTime();
		this.cawlTime = cawlTime;
		ruleList = new ArrayList<Rule>();
		constraintList = new ArrayList<Constraint>();
		contextCondition = new ContextCondition();
		
		contextComparator = new ContextComparator();
		invokeList = new ArrayList<Invoke>();
		invoke = new Invoke();
		subFlow = new Flow();
		subFlowLinkList = new ArrayList<Link>();
		subFlowNodeList = new ArrayList<NodeC>();
		activeNode = new ActiveNode();
		activeNodeList = new ArrayList<ActiveNode>();
		checkNodeList = new ArrayList<ActiveNode>();
		
		serviceAgent = new ServiceAgent();
		serviceProviderLocation = "";
	}
	
	public void run() {
		cawlState.systemLogAccess("set", cawlTime.getTime() + "[ Node : " + node.getName() + " start ]");
		cawlState.cawlNodesAccess("set", node.getName());
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + node.getName() + " condition checking...");
		
		ruleList = node.getCondition().getContext().getRuleList();
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
		
		invokeList = node.getInvokeList();
		for(int i = 0; i < invokeList.size(); i++) {
			invoke = invokeList.get(i);
			
			// SerivceProvider
//			if(!invoke.getOperation().equals("")) {
//				cawlState.systemLogAccess("set", cawlTime.getTime() + node.getName() + " service sending...");
//				
//				serviceProvider = serviceProviderList.get(0);
//				serviceProviderLocation = serviceProvider.getLocation();
//				
//				serviceAgent.sendService(serviceProviderLocation, invoke.getOperation());
//				
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
			
			// Subflow
			if(!invoke.getSubflow().equals("")) {
				for(int j = 0; j < flowList.size(); j++) {
					if(invoke.getSubflow().equals(flowList.get(j).getName())) {
						subFlow = flowList.get(j);
						break;
					}
				}
				
				cawlState.systemLogAccess("set", cawlTime.getTime() + subFlow.getName() + " running...");
				
				subFlowLinkList = subFlow.getLinkList();
				
				String subFlowSource = subFlow.getSource().getName();
				String subFlowFirstNode = "";
				for(int j = 0; j < subFlowLinkList.size(); j++) {
					if(subFlowSource.equals(subFlowLinkList.get(j).getFrom())) {
						subFlowFirstNode = subFlowLinkList.get(j).getTo();
					}
				}
				
				subFlowNodeList = subFlow.getNodeList();
				
				activeNode.setNodeName(subFlowFirstNode);
				activeNodeList.add(activeNode);
				
				String subFlowSink = subFlow.getSink().getName();
				
				try {
					while(true) {
						if(WorkflowControll.getTaskControll()) {
							// Node Execute
							for(int j = 0; j < subFlowNodeList.size(); j++) {
								for(int k = 0; k < activeNodeList.size(); k++) {
									if(subFlowNodeList.get(j).getName().equals(activeNodeList.get(k).getNodeName())) {
										subFlowNodeList.get(j).setState("Activate");
										nodeThreadManager.manage(mainFlowSink, taskCheck, ontologyInfo, subFlowNodeList.get(j), flowList, serviceProviderList, nodeThreadManager, cawlState, cawlTime);
									}
								}
							}
							
							// Node Execute Check
							for(int j = 0; j < activeNodeList.size(); j++) {
								for(int k = 0; k < subFlowNodeList.size(); k++) {
									if(activeNodeList.get(j).getNodeName().equals(subFlowNodeList.get(k).getName())) {
										
										while(true) {
											if(subFlowNodeList.get(k).getState().equals("deActivate")) {
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
							for(int j = 0; j < activeNodeList.size(); j++) {
								String temp = activeNodeList.get(j).getNodeName();
								activeNode = new ActiveNode();
								activeNode.setNodeName(temp);
								checkNodeList.add(activeNode);
							}
							
							int count = 0;
							activeNodeList.clear();
							for(int j = 0; j < subFlowLinkList.size(); j++) {
								for(int k = 0; k < checkNodeList.size(); k++) {
									
									if(subFlowLinkList.get(j).getFrom().equals(checkNodeList.get(k).getNodeName())) {
										String nextCheck = subFlowLinkList.get(j).getTo();
										
										if(count == 0) {
											activeNode = new ActiveNode();
											activeNode.setNodeName(nextCheck);
											activeNodeList.add(activeNode);
											count++;
										} else {
											for(int l = 0; l < activeNodeList.size(); l++) {
												
												if(!nextCheck.equals(activeNodeList.get(l).getNodeName())) {
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
								if(activeNodeList.get(j).getNodeName().equals(subFlowSink)) {
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
				
				cawlState.systemLogAccess("set", cawlTime.getTime() + subFlow.getName() + " complete");
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < flowList.size(); i++) {
			Flow flowTemp = new Flow();
			flowTemp = flowList.get(i);
			
			ArrayList<Link> linkListTemp = new ArrayList<Link>();
			linkListTemp = flowTemp.getLinkList();
			
			for(int j = 0; j < linkListTemp.size(); j++) {
				if(linkListTemp.get(j).getFrom().equals(node.getName())) {
					if(linkListTemp.get(j).getTo().equals(mainFlowSink)) {
						taskCheck.setTaskCheck();
					}
				}
			}
		}
		
		cawlState.systemLogAccess("set", cawlTime.getTime() + "[ Node : " + node.getName() + " end ]");
		cawlState.cawlNodesAccess("delete", node.getName());
		node.setState("deActivate");
	}
}
