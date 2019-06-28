package CAWLEngine.Parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import CAWLEngine.WorkflowPool.Activate;
import CAWLEngine.WorkflowPool.Activator;
import CAWLEngine.WorkflowPool.BaseOntologies;
import CAWLEngine.WorkflowPool.CAWL;
import CAWLEngine.WorkflowPool.Case;
import CAWLEngine.WorkflowPool.Condition;
import CAWLEngine.WorkflowPool.Constraint;
import CAWLEngine.WorkflowPool.Context;
import CAWLEngine.WorkflowPool.Event;
import CAWLEngine.WorkflowPool.Flow;
import CAWLEngine.WorkflowPool.From;
import CAWLEngine.WorkflowPool.Initialize;
import CAWLEngine.WorkflowPool.Invoke;
import CAWLEngine.WorkflowPool.Link;
import CAWLEngine.WorkflowPool.Message;
import CAWLEngine.WorkflowPool.NodeC;
import CAWLEngine.WorkflowPool.Ontology;
import CAWLEngine.WorkflowPool.Part;
import CAWLEngine.WorkflowPool.Rule;
import CAWLEngine.WorkflowPool.Service;
import CAWLEngine.WorkflowPool.ServiceProvider;
import CAWLEngine.WorkflowPool.Sink;
import CAWLEngine.WorkflowPool.Source;
import CAWLEngine.WorkflowPool.Variable;
import CAWLEngine.WorkflowPool.Wait;
import CAWLEngine.WorkflowPool.WorkflowPool;

public class ParserHandler {
	private CAWL cawl;
	private Message message;
	private Part part;
	private Variable variable;
	private Initialize initialize;
	private From from;
	private BaseOntologies baseOntologies;
	private Ontology ontology;
	private ServiceProvider serviceProvider;
	private Service service;
	private Activator activator;
	private Activate activate;
	private Condition condition;
	private Case casec;
	private Event event;
	private Context context;
	private Rule rule;
	private Constraint constraint;
	private Flow flow;
	private Source source;
	private NodeC node;
	private Wait wait;
	private Invoke invoke;
	private Sink sink;
	private Link link;
	
	public void parsing(File xmlFilePath, WorkflowPool workflowPool) {
		String tagName = "cawl";
		
		cawl = new CAWL();
		workflowPool.setCAWL(cawl);
		cawl = workflowPool.getCAWL();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFilePath);
			
			Element eCAWL = doc.getDocumentElement();
			Node nCAWL = (Node)eCAWL;
			
			NamedNodeMap attrs = nCAWL.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						cawl.setName(attribute.getValue());
						break;
					case "namespace":
						cawl.setNamespace(attribute.getValue());
						break;
					case "version":
						cawl.setVersion(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nCAWL.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName,node);
						break;
					case "message":
						message(tagName,node);
						break;
					case "variable":
						variable(tagName,node);
						break;
					case "baseOntologies":
						baseOntologies(tagName,node);
						break;
					case "serviceProvider":
						serviceProvider(tagName,node);
						break;
					case "activator":
						activator(tagName,node);
						break;
					case "flow":
						flow(tagName,node);
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void documentation(String sTagName, Node nNode) {
		if(sTagName.equals("cawl")) {
			cawl.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("cawlMessage")) {
			message.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("cawlMessagePart")) {
			part.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("cawlVariable")) {
			variable.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("baseOntologies")) {
			baseOntologies.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("ontoloty")) {
			ontology.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("serviceProvider")) {
			serviceProvider.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("service")) {
			service.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activator")) {
			activator.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorMessage")) {
			message.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorMessagePart")) {
			part.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorVariable")) {
			variable.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activate")) {
			activate.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorCondition")) {
			condition.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorConditionCase")) {
			casec.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorConditionCaseEvent")) {
			event.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorConditionContext")) {
			context.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorConditionContextRule")) {
			rule.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("activatorConditionContextRuleConstraint")) {
			constraint.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("flow")) {
			flow.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("flowMessage")) {
			message.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("flowMessagePart")) {
			part.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("flowVariable")) {
			variable.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("source")) {
			source.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("node")) {
			node.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeMessage")) {
			message.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeMessagePart")) {
			part.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeVariable")) {
			variable.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("wait")) {
			wait.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeCondition")) {
			condition.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionCase")) {
			casec.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionCaseEvent")) {
			event.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContext")) {
			context.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContextRule")) {
			rule.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContextRuleConstraint")) {
			constraint.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("invoke")) {
			invoke.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("sink")) {
			sink.setDocumentation(nNode.getTextContent());
		} else if(sTagName.equals("link")) {
			link.setDocumentation(nNode.getTextContent());
		} else {}
	}
	
	public void message(String sTagName, Node nNode) {
		String tagName;
		message = new Message();
		
		if(sTagName.equals("cawl")) {
			tagName = "cawlMessage";
			cawl.setMessage(message);
			message = cawl.getMessage();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						message.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "part":
						part(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("activator")) {
			tagName = "activatorMessage";
			activator.setMessage(message);
			message = activator.getMessage();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						message.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "part":
						part(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("flow")) {
			tagName = "flowMessage";
			flow.setMessage(message);
			message = flow.getMessage();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						message.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "part":
						part(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("node")) {
			tagName = "nodeMessage";
			node.setMessage(message);
			message = node.getMessage();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						message.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "part":
						part(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void part(String sTagName, Node nNode) {
		String tagName;
		part = new Part();
		
		if(sTagName.equals("cawlMessage")) {
			tagName = "cawlMessagePart";
			message.setPart(part);
			part = message.getPart();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						part.setName(attribute.getValue());
						break;
					case "type":
						part.setType(attribute.getValue());
						break;
					case "element":
						part.setElement(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("activatorMessage")) {
			tagName = "activatorMessagePart";
			message.setPart(part);
			part = message.getPart();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						part.setName(attribute.getValue());
						break;
					case "type":
						part.setType(attribute.getValue());
						break;
					case "element":
						part.setElement(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("flowMessage")) {
			tagName = "flowMessagePart";
			message.setPart(part);
			part = message.getPart();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						part.setName(attribute.getValue());
						break;
					case "type":
						part.setType(attribute.getValue());
						break;
					case "element":
						part.setElement(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeMessage")) {
			tagName = "nodeMessagePart";
			message.setPart(part);
			part = message.getPart();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						part.setName(attribute.getValue());
						break;
					case "type":
						part.setType(attribute.getValue());
						break;
					case "element":
						part.setElement(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void variable(String sTagName, Node nNode) {
		String tagName;
		variable = new Variable();
		
		if(sTagName.equals("cawl")) {
			tagName = "cawlVariable";
			cawl.setVariable(variable);
			variable = cawl.getVariable();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						variable.setName(attribute.getValue());
						break;
					case "type":
						variable.setType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "initialize":
						initialize(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("activator")) {
			tagName = "activatorVariable";
			activator.setVariable(variable);
			variable = activator.getVariable();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						variable.setName(attribute.getValue());
						break;
					case "type":
						variable.setType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "initialize":
						initialize(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("flow")) {
			tagName = "flowVariable";
			flow.setVariable(variable);
			variable = flow.getVariable();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						variable.setName(attribute.getValue());
						break;
					case "type":
						variable.setType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "initialize":
						initialize(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("node")) {
			tagName = "nodeVariable";
			node.setVariable(variable);
			variable = node.getVariable();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						variable.setName(attribute.getValue());
						break;
					case "type":
						variable.setType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "initialize":
						initialize(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void initialize(String sTagName, Node nNode) {
		String tagName;
		initialize = new Initialize();
		
		if(sTagName.equals("cawlVariable")) {
			tagName = "cawlVariableInitialize";
			variable.setInitialize(initialize);
			initialize = variable.getInitialize();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "part":
						initialize.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "from":
						from(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("activatorVariable")) {
			tagName = "activatorVariableInitialize";
			variable.setInitialize(initialize);
			initialize = variable.getInitialize();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "part":
						initialize.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "from":
						from(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("flowVariable")) {
			tagName = "flowVariableInitialize";
			variable.setInitialize(initialize);
			initialize = variable.getInitialize();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "part":
						initialize.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "from":
						from(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeVariable")) {
			tagName = "nodeVariableInitialize";
			variable.setInitialize(initialize);
			initialize = variable.getInitialize();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "part":
						initialize.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "from":
						from(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void from(String sTagName, Node nNode) {
		from = new From();
		
		if(sTagName.equals("cawlVariableInitialize")) {
			initialize.setFrom(from);
			from = initialize.getFrom();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						from.setExpression(attribute.getValue());
						break;
					case "variable":
						from.setVariable(attribute.getValue());
						break;
					case "part":
						from.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("activatorVariableInitialize")) {
			initialize.setFrom(from);
			from = initialize.getFrom();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						from.setExpression(attribute.getValue());
						break;
					case "variable":
						from.setVariable(attribute.getValue());
						break;
					case "part":
						from.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("flowVariableInitialize")) {
			initialize.setFrom(from);
			from = initialize.getFrom();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						from.setExpression(attribute.getValue());
						break;
					case "variable":
						from.setVariable(attribute.getValue());
						break;
					case "part":
						from.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeVariableInitialize")) {
			initialize.setFrom(from);
			from = initialize.getFrom();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						from.setExpression(attribute.getValue());
						break;
					case "variable":
						from.setVariable(attribute.getValue());
						break;
					case "part":
						from.setPart(attribute.getValue());
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void baseOntologies(String sTagName, Node nNode) {
		String tagName;
		baseOntologies = new BaseOntologies();
		
		if(sTagName.equals("cawl")) {
			tagName = "baseOntologies";
			cawl.setBaseOntologies(baseOntologies);
			baseOntologies = cawl.getBaseOntologies();
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "ontology":
						ontoloty(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void ontoloty(String sTagName, Node nNode) {
		String tagName;
		ontology = new Ontology();
		
		if(sTagName.equals("baseOntologies")) {
			tagName = "ontoloty";
			baseOntologies.setOntology(ontology);
			ontology = baseOntologies.getOntology();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "location":
						ontology.setLocation(attribute.getValue());
						break;
					case "namespace":
						ontology.setNamespace(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void serviceProvider(String sTagName, Node nNode) {
		String tagName;
		serviceProvider = new ServiceProvider();
		
		if(sTagName.equals("cawl")) {
			tagName = "serviceProvider";
			cawl.setServiceProvider(serviceProvider);
			serviceProvider = cawl.getServiceProvider();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						serviceProvider.setName(attribute.getValue());
						break;
					case "location":
						serviceProvider.setLocation(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "service":
						service(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void service(String sTagName, Node nNode) {
		String tagName;
		service = new Service();
		
		if(sTagName.equals("serviceProvider")) {
			tagName = "service";
			serviceProvider.setService(service);
			service = serviceProvider.getService();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "operation":
						service.setOperation(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void activator(String sTagName, Node nNode) {
		String tagName;
		activator = new Activator();
		
		if(sTagName.equals("cawl")) {
			tagName = "activator";
			cawl.setActivator(activator);
			activator = cawl.getActivator();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						activator.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "message":
						message(tagName, node);
						break;
					case "variable":
						variable(tagName, node);
						break;
					case "activate":
						activate(tagName, node);
						break;
					case "condition":
						condition(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void activate(String sTagName, Node nNode) {
		String tagName;
		activate = new Activate();
		
		if(sTagName.equals("activator")) {
			tagName = "activate";
			activator.setActivate(activate);
			activate = activator.getActivate();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "flow":
						activate.setFlow(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void condition(String sTagName, Node nNode) {
		String tagName;
		condition = new Condition();
		
		if(sTagName.equals("activator")) {
			tagName = "activatorCondition";
			activator.setCondition(condition);
			condition = activator.getCondition();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						condition.setExpression(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "case":
						casef(tagName, node);
						break;
					case "context":
						context(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("node")) {
			tagName = "nodeCondition";
			node.setCondition(condition);
			condition = node.getCondition();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "expression":
						condition.setExpression(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "case":
						casef(tagName, node);
						break;
					case "context":
						context(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void casef(String sTagName, Node nNode) {
		String tagName;
		casec = new Case();
		
		if(sTagName.equals("activatorCondition")) {
			tagName = "activatorConditionCase";
			condition.setCase(casec);
			casec = condition.getCase();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						casec.setName(attribute.getValue());
						break;
					case "expression":
						casec.setExpression(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "event":
						event(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeCondition")) {
			tagName = "nodeConditionCase";
			condition.setCase(casec);
			casec = condition.getCase();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						casec.setName(attribute.getValue());
						break;
					case "expression":
						casec.setExpression(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "event":
						event(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void event(String sTagName, Node nNode) {
		String tagName;
		event = new Event();
		
		if(sTagName.equals("activatorConditionCase")) {
			tagName = "activatorConditionCaseEvent";
			casec.setEvent(event);
			event = casec.getEvent();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "operation":
						event.setOperation(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeConditionCase")) {
			tagName = "nodeConditionCaseEvent";
			casec.setEvent(event);
			event = casec.getEvent();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "operation":
						event.setOperation(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void context(String sTagName, Node nNode) {
		String tagName;
		context = new Context();
		
		if(sTagName.equals("activatorCondition")) {
			tagName = "activatorConditionContext";
			condition.setContext(context);
			context = condition.getContext();
					
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						context.setName(attribute.getValue());
						break;
					case "priority":
						context.setPriority(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "rule":
						rule(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeCondition")) {
			tagName = "nodeConditionContext";
			condition.setContext(context);
			context = condition.getContext();
					
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						context.setName(attribute.getValue());
						break;
					case "priority":
						context.setPriority(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "rule":
						rule(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void rule(String sTagName, Node nNode) {
		String tagName;
		rule = new Rule();
		
		if(sTagName.equals("activatorConditionContext")) {
			tagName = "activatorConditionContextRule";
			context.setRule(rule);
			rule = context.getRule();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						rule.setName(attribute.getValue());
						break;
					case "expression":
						rule.setExpression(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "constraint":
						constraint(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeConditionContext")) {
			tagName = "nodeConditionContextRule";
			context.setRule(rule);
			rule = context.getRule();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
				case "name":
					rule.setName(attribute.getValue());
					break;
				case "expression":
					rule.setExpression(attribute.getValue());
					break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "constraint":
						constraint(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void constraint(String sTagName, Node nNode) {
		String tagName;
		constraint = new Constraint();
		
		if(sTagName.equals("activatorConditionContextRule")) {
			tagName = "activatorConditionContextRuleConstraint";
			rule.setConstraint(constraint);
			constraint = rule.getConstraint();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						constraint.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "subject":
						subject(tagName, node);
						break;
					case "verb":
						verb(tagName, node);
						break;
					case "object":
						object(tagName, node);
						break;
					default:
						break;
				}
			}
		} else if(sTagName.equals("nodeConditionContextRule")) {
			tagName = "nodeConditionContextRuleConstraint";
			rule.setConstraint(constraint);
			constraint = rule.getConstraint();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						constraint.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "subject":
						subject(tagName, node);
						break;
					case "verb":
						verb(tagName, node);
						break;
					case "object":
						object(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void subject(String sTagName, Node nNode) {
		if(sTagName.equals("activatorConditionContextRuleConstraint")) {
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "type":
						constraint.setSubjectType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			constraint.setSubject(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContextRuleConstraint")) {
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "type":
						constraint.setSubjectType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			constraint.setSubject(nNode.getTextContent());
		} else {}
	}
	
	public void verb(String sTagName, Node nNode) {
		if(sTagName.equals("activatorConditionContextRuleConstraint")) {
			constraint.setVerb(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContextRuleConstraint")) {
			constraint.setVerb(nNode.getTextContent());
		} else {}
	}
	
	public void object(String sTagName, Node nNode) {
		if(sTagName.equals("activatorConditionContextRuleConstraint")) {
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "type":
						constraint.setObjectType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			constraint.setObject(nNode.getTextContent());
		} else if(sTagName.equals("nodeConditionContextRuleConstraint")) {
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "type":
						constraint.setObjectType(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			constraint.setObject(nNode.getTextContent());
		} else {}
	}
	
	public void flow(String sTagName, Node nNode) {
		String tagName;
		flow = new Flow();
		
		if(sTagName.equals("cawl")) {
			tagName = "flow";
			cawl.setFlow(flow);
			flow = cawl.getFlow();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						flow.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "message":
						message(tagName, node);
						break;
					case "variable":
						variable(tagName, node);
						break;
					case "source":
						source(tagName, node);
						break;
					case "node":
						node(tagName, node);
						break;
					case "sink":
						sink(tagName, node);
						break;
					case "link":
						link(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void source(String sTagName, Node nNode) {
		String tagName;
		source = new Source();
		
		if(sTagName.equals("flow")) {
			tagName = "source";
			flow.setSource(source);
			source = flow.getSource();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						source.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void node(String sTagName, Node nNode) {
		String tagName;
		node = new NodeC();
		
		if(sTagName.equals("flow")) {
			tagName = "node";
			flow.setNode(node);
			node = flow.getNode();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						node.setName(attribute.getValue());
						break;
					case "state":
						node.setState(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					case "message":
						message(tagName, node);
						break;
					case "variable":
						variable(tagName, node);
						break;
					case "wait":
						wait(tagName, node);
						break;
					case "condition":
						condition(tagName, node);
						break;
					case "invoke":
						invoke(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void wait(String sTagName, Node nNode) {
		String tagName;
		wait = new Wait();
		
		if(sTagName.equals("node")) {
			tagName = "wait";
			node.setWait(wait);
			wait = node.getWait();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "joinCondition":
						wait.setJoinCondition(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void invoke(String sTagName, Node nNode) {
		String tagName;
		invoke = new Invoke();
		
		if(sTagName.equals("node")) {
			tagName = "invoke";
			node.setInvoke(invoke);
			invoke = node.getInvoke();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "operation":
						invoke.setOperation(attribute.getValue());
						break;
					case "subflow":
						invoke.setSubflow(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void sink(String sTagName, Node nNode) {
		String tagName;
		sink = new Sink();
		
		if(sTagName.equals("flow")) {
			tagName = "sink";
			flow.setSink(sink);
			sink = flow.getSink();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "name":
						sink.setName(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
	
	public void link(String sTagName, Node nNode) {
		String tagName;
		link = new Link();
		
		if(sTagName.equals("flow")) {
			tagName = "link";
			flow.setLink(link);
			link = flow.getLink();
			
			NamedNodeMap attrs = nNode.getAttributes();
			
			for(int i = 0; i < attrs.getLength(); i++) {
				Attr attribute = (Attr)attrs.item(i);
				
				switch(attribute.getName()) {
					case "from":
						link.setFrom(attribute.getValue());
						break;
					case "to":
						link.setTo(attribute.getValue());
						break;
					default:
						break;
				}
			}
			
			for(Node node = nNode.getFirstChild(); node != null; node = node.getNextSibling()) {
				switch(node.getNodeName()) {
					case "documentation":
						documentation(tagName, node);
						break;
					default:
						break;
				}
			}
		} else {}
	}
}
