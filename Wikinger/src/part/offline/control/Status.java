package part.offline.control;

public class Status {
	
	private int runningThreads;
	private int[] workForEach;
	private int[] workForEachDone;
	
	private double percentDone;
	private long timeRemaining;
	private long startTime;
	
	
	
	
	
	private Status(int runningThreads){
		this.runningThreads = runningThreads;
		workForEach = new int[runningThreads];
		workForEachDone = new int[runningThreads];
		
		startTime = System.currentTimeMillis();
	}
	
	public Status(int runningThreads, int workForEach){
		this(runningThreads);
		for (int i = 0; i < this.workForEach.length; i++) {
			this.workForEach[i] = workForEach;
		}
	}
	
	public Status(int runningThreads, int[] workForEach){
		this(runningThreads);
		for (int i = 0; i < this.workForEach.length; i++) {
			this.workForEach[i] = workForEach[i];
		}
		
	}
	
	public void calcStatus(){
		long currentTime = System.currentTimeMillis();
		percentDone = 0.0;	
		
		for (int i = 0; i < runningThreads; i++) {
			percentDone  += (1.0)*workForEachDone[i]/workForEach[i];
		}	
		percentDone /= (1.0)*runningThreads;
		
		currentTime = currentTime-startTime;
		
		//System.out.println("TimeOver: "+currentTime/1000);
		
		timeRemaining = (long) ((1.0/percentDone-1)*currentTime);
	}
	
	public long getTimeRemaining(){
		return timeRemaining;
	}
	
	public double getPercent(){
		return percentDone;
	}
	
	public void setWorkForEachDone(int done, int threadID){
		workForEachDone[threadID] = done;
	}
	
	

}
