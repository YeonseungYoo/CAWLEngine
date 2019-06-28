package CAWLEngine.ContextComparator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import CAWLEngine.TaskManager.TaskCheck;

public class ContextComparator {
	
	public void ontologySynchronize(String ontologyInfo, String allContextInfo) {
		// Want Context Synchronize Code Implement
	}
	
	public void contextCheck(String ontologyInfo, String jsonContext) {
		String response = "";
		
		while(true) {
			try {
				URL postUrl = new URL(ontologyInfo);
				HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		        connection.setDoOutput(true);
		        connection.setInstanceFollowRedirects(false);
		        connection.setRequestMethod("POST");
		        connection.setRequestProperty("Content-Type", "application/json");
		        OutputStream os= connection.getOutputStream();
		        os.write(jsonContext.getBytes());
		        os.flush();
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		        
		        response = br.readLine();
		        
//		        System.out.println("Ontology Output : " + response); // contextEngine response Value
		        
//		        //
//		        System.out.println(jsonContext + ", " + response);
//		        //
		        
		        connection.disconnect();
		        
		        if(response.equals("true")) {
		        	break;
		        }
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
