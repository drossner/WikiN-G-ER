package part.online.control;

public class WeightingUnit extends Thread{

	private int start;
	private int end;
	private int[] entities;

	public WeightingUnit(int start, int end, int[] entities) {
		this.start = start;
		this.end = end;
		this.entities = entities;
	}
	
	public void run(){
		for (int i = start; i <= end; i++) {
			
		}
	}
}
