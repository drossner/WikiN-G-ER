package part.offline.control;

/**
 * Status Klasse welche den aktuellen Status hält und kalkuliert. Die Methode calcStatus() sollte vor jedem get() aufgerufen werden um auf dem akutellsten Stand zu sein.
 * @author Daniel
 *
 */
public class Status {
	
	private int runningThreads;
	private int[] workForEach;
	private int[] workForEachDone;
	private long workSum;
	
	private double percentDone;
	private long timeRemaining;
	private long startTime;
	
	
	
	
	
	private Status(int runningThreads){
		this.runningThreads = runningThreads;
		workForEach = new int[runningThreads];
		workForEachDone = new int[runningThreads];
		workSum = 0;
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @param runningThreads Anzahl der Threads welche Arbeit verrichten
	 * @param workForEach Der Workload welcher von jedem Thread abgearbeitet werden muss (für jeden einzeln)
	 */
	public Status(int runningThreads, int workForEach){
		this(runningThreads);
		for (int i = 0; i < this.workForEach.length; i++) {
			this.workForEach[i] = workForEach;
		}
		workSum = runningThreads* workForEach;
	}
	
	/**
	 * 
	 * @param runningThreads Anzahl der Threads welche Arbeit verrichten
	 * @param workForEach Für jeden Thread kann ein individueller Workload übergeben werden
	 */
	public Status(int runningThreads, int[] workForEach){
		this(runningThreads);
		for (int i = 0; i < this.workForEach.length; i++) {
			this.workForEach[i] = workForEach[i];
			workSum += workForEach[i];
		}
		
	}
	
	/**
	 * Setzt TimeRemaning und PercentDone auf den akutellen Status
	 */
	public void calcStatus(){
		long currentTime = System.currentTimeMillis();
		percentDone = 0.0;	
		long workDone = 0;
		
		for (int i = 0; i < runningThreads; i++) {
			workDone += workForEachDone[i];
		}	
		percentDone = ((1.0)*workDone)/workSum;
		
		currentTime = currentTime-startTime;
		
		//System.out.println("TimeOver: "+currentTime/1000);
		
		timeRemaining = (long) ((1.0/percentDone-1)*currentTime);
	}
	
	/**
	 * setzt die variablen zurück, damit das objekt nochmals benutzt werden kann
	 */
	public void reset(){
		this.percentDone = 0.0;
		this.startTime = 0;
		
		this.workForEachDone = new int[workForEachDone.length];
	}
	
	/**
	 * calcStatus() sollte vorher aufgerufen werden, um auf dem aktuellsten Stand zu sein
	 * @return Verbleibende Zeit in ms
	 */
	public long getTimeRemaining(){
		return timeRemaining;
	}
	/**
	 * calcStatus() sollte vorher aufgerufen werden, um auf dem aktuellsten Stand zu sein
	 * @return Abgeschlossene Arbeit in Prozent
	 */
	public double getPercent(){
		return percentDone;
	}
	
	/**
	 * 
	 * @return startTime
	 */
	
	public long getStartTime(){
		return startTime;
	}
	
	/**
	 * 
	 * @param done
	 * @param threadID
	 */
	public void setWorkForEachDone(int done, int threadID){
		workForEachDone[threadID] = done;
	}
	
	

}
