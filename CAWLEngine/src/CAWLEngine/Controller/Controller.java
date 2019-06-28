package CAWLEngine.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import CAWLEngine.Monitor.CAWLSearch;
import CAWLEngine.Monitor.CAWLState;
import CAWLEngine.Monitor.CAWLTime;
import CAWLEngine.Parser.Parser;
import CAWLEngine.TaskManager.TaskManager;
import CAWLEngine.WorkflowPool.CAWL;
import CAWLEngine.WorkflowPool.WorkflowPool;

@ServerEndpoint("/Controller")
public class Controller {
	private WorkflowPool workflowPool;
	private Parser parser;
	private TaskManager taskManager;
	
	private CAWL recentCAWL;
	private CAWLSearch cawlSearch;
	private CAWLState cawlState;
	private CAWLTime cawlTime;
	
	private JSONParser jSONParser;
	private JSONObject jsonObject;
	
	private BufferedOutputStream bos;
	private File cawlScenarioDocument;
	
	public Controller() {
		workflowPool = new WorkflowPool();
        parser = new Parser();
        taskManager = new TaskManager();
        
        recentCAWL = new CAWL();
        cawlSearch = new CAWLSearch();
        cawlState = new CAWLState();
        cawlTime = new CAWLTime();
        
        jSONParser = new JSONParser();
        jsonObject = new JSONObject();
        
        WorkflowControll workflowControll = new WorkflowControll();
	}
	
    @OnOpen
    public void handleOpen(Session session) {
    }
    
    @OnMessage
    public String handleMessage(Session session, String message) {
    	String value = "";
    	
    	try {
			jsonObject = (JSONObject)jSONParser.parse(message);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	switch(jsonObject.get("key").toString()) {
	    	case "fileUploadStart":
	    		cawlState.systemLogAccess("set", cawlTime.getTime() + "CAWLEngine server running...");
	    		
	    		String fileName = jsonObject.get("fileName").toString();
	    		cawlScenarioDocument = new File("C:\\Dev\\workspace-cawl_engine_ys\\CAWLEngine\\CAWL_Document\\" + fileName);
	    		
	    		try {
	    			bos = new BufferedOutputStream(new FileOutputStream(cawlScenarioDocument));
	    		} catch(FileNotFoundException e) {
	    			e.printStackTrace();
	    		}
	    		break;
	    	case "fileUploadEnd":
	    		try {
	    			bos.flush();
	    			bos.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		cawlState.systemLogAccess("set", cawlTime.getTime() + "CAWL Scenario Document upload complete");
	    		
	    		parser.parse(cawlScenarioDocument, workflowPool);
	    		cawlState.systemLogAccess("set", cawlTime.getTime() + "CAWL Scenario Document parsing complete");
	    		
	    		recentCAWL = workflowPool.getCAWL();
				taskManager.manageStart(workflowPool, cawlState, cawlTime);
				
				value = cawlSearch.search(recentCAWL);
	    		break;
	    	case "workflowStop":
	    		taskManager.manageStop(cawlState, cawlTime);
	    		break;
	    	case "workflowTaskControll":
	    		WorkflowControll.setTaskControll((Boolean) jsonObject.get("taskControll"));
	    		break;
	    	case "stateCheck":
	    		value = cawlState.check();
	    		break;
	    	default:
	    		break;
    	}
    	
    	return value;
    }
    
    @OnMessage
    public void processUpload(ByteBuffer message, boolean last, Session session) {
    	while(message.hasRemaining()) {
    		try {
    			bos.write(message.get());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    @OnClose
    public void handleClose(Session session) {
    }
    
    @OnError
    public void handleError(Session session, Throwable t) {
        t.printStackTrace();
    }
}