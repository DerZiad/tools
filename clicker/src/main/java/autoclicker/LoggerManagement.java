package autoclicker;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManagement {

	private Logger logger = Logger.getLogger("Logs");
	private FileHandler fh = null;

	private static LoggerManagement instance;

	private LoggerManagement(String filename) {
		try {
			fh = new FileHandler(filename);
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LoggerManagement getInstance() {
		if (instance == null)
			instance = new LoggerManagement("logger.txt");
		return instance;
	}

	public synchronized void log(Level level, String msg,Throwable error) {
		logger.log(level, msg,error);
	}
	
	public synchronized void log(Level level, String msg) {
		logger.log(level, msg);
	}


	public void close() {
		fh.close();
	}

}
