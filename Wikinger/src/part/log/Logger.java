package part.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.control.FileOutput;
import part.offline.control.Status;

public class Logger implements Runnable{
	
	private FileOutput out;
	private Status status;
	private SimpleDateFormat format;

	public Logger(String pathName, Status status) {
		this.status = status;
		this.out = new FileOutput(true, pathName);
		format = new SimpleDateFormat("dd.mm.yyyy HH.mm.ss");
	}

	public void run() {
		double percent = status.getPercent();
		StringBuilder builder = new StringBuilder();
		
		while(percent <= 1){
			builder.append(format.format((System.currentTimeMillis())));
			builder.append("	");
			builder.append(percent);
			builder.append("	");
			builder.append(status.getTimeRemaining());
			out.writeToFile(builder);
			builder = new StringBuilder();
			percent = status.getPercent();
			
			try {
				synchronized (this) {
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
