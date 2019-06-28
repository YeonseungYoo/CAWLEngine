package CAWLEngine.Monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CAWLTime {

	public synchronized String getTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", new Locale("en", "US"));
		String currentTime = dayTime.format(new Date(time));
		return currentTime + "  ";
	}
}
