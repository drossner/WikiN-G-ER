package part.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import data.control.FileOutput;
import part.offline.control.Status;

public class Logger implements Runnable{
	
	private FileOutput out;
	private Status status;
	private SimpleDateFormat format;

	public Logger(String pathName, Status status) {
		this.status = status;
		this.out = new FileOutput(true, pathName);
		format = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss");
	}

	public void run() {
		status.calcStatus();
		double percent = status.getPercent();			//Prozent noch schöner ausschauen lassen !
		StringBuilder builder = new StringBuilder();
		
		while(percent <= 1){
			status.calcStatus();
			builder.append(format.format((System.currentTimeMillis())));
			builder.append("\t");
			builder.append(percent);
			builder.append("\t");
			long time = (System.currentTimeMillis() - status.getStartTime());
			builder.append("\tellapsed time: " + getTime(time));
			builder.append("\ttime remaining: " + getTime(status.getTimeRemaining()));
			out.writeToFile(builder);
			builder = new StringBuilder();
			percent = status.getPercent();
			
			try {
				synchronized (this) {
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getTime(long time) {		//TODO: Auslagern in Status
		String s = String.format("%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(time),
				TimeUnit.MILLISECONDS.toMinutes(time)%60,
				TimeUnit.MILLISECONDS.toSeconds(time)%60);
		return s;
	}

}
