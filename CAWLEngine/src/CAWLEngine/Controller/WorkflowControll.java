package CAWLEngine.Controller;

public class WorkflowControll {
	static boolean taskControll;
	
	WorkflowControll() {
		this.taskControll = true;
	}
	
	public static boolean getTaskControll() {
		return taskControll;
	}
	
	public static void setTaskControll(boolean value) {
		taskControll = value;
	}
}
