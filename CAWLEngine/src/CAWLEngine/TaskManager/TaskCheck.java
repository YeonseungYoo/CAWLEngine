package CAWLEngine.TaskManager;

public class TaskCheck {
	private boolean taskCheck;
	
	public TaskCheck() {
		taskCheck = false;
	}
	
	public synchronized void setTaskCheck() {
		taskCheck = true;
	}
	
	public synchronized boolean getTaskCheck() {
		return taskCheck;
	}
}
