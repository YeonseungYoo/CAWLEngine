package CAWLEngine.ServiceProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceAgent {
	private Socket mSocket;
	
	private BufferedReader mIn;
	private PrintWriter mOut;
	
	public void sendService(String location, String operation) {
		String[] locationList = location.split(":");
		String locationIP = locationList[0];
		String locationPORT = locationList[1];
		int locationPORT_int = Integer.parseInt(locationPORT);
		
		try {
			mSocket = new Socket(locationIP, locationPORT_int);
			
			mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			mOut = new PrintWriter(mSocket.getOutputStream());
			
			mOut.println(operation);
			mOut.flush();
			
//			mIn.readLine(); // Service Agent Return Value;
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				mIn.close();
				mOut.close();
				mSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
