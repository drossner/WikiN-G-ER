package part.offline.test;

import part.offline.data.Gazetter;

public class GazetterLoadTest {
	
	public static void main(String []Args){
		new GazetterLoadTest();
	}
	
	public GazetterLoadTest() {
		String arr[];
		Gazetter gaz = new Gazetter("./gazetteer.csv");
		arr = gaz.loadGaz();
		for (int i = 0; i<arr.length; i++){
			System.out.println(i + " Stelle: " + arr[i]);
		}
	}

}
