package test;

import part.offline.control.Status;

public class StatusTest {
	
	public static void main(String[] args) {
		Status t = new Status(3, 1000);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		t.setWorkForEachDone(150, 0);
		t.setWorkForEachDone(128, 1);
		t.setWorkForEachDone(298, 2);
		
		t.calcStatus();
		
		System.out.println("Prozent abgeschlossen: "+t.getPercent()+"   TimeRemaining: "+t.getTimeRemaining()/1000);
		
		
	}

}
