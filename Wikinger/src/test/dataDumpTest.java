package test;

import java.util.ArrayList;

import data.City;
import data.DataDump;
import data.Entity;

public class dataDumpTest {
	
	public static void main(String[] args) {
		ArrayList<Entity> entitäten = new ArrayList<Entity>();
		for (int i = 0; i < 10; i++) {
			entitäten.add(new Entity("test1", "organisation"));
		}
		for (int i = 0; i < 10; i++) {
			entitäten.add(new Entity("test2", "organisation"));
		}
		for (int i = 0; i < 10; i++) {
			entitäten.add(new Entity("test1", "organisation"));
		}
		for (int i = 0; i < 10; i++) {
			entitäten.add(new Entity("test1", "fickdenhahn"));
		}
		
		DataDump dd = new DataDump(new City("Hof", 0.0, 0.0), entitäten);
		
		System.out.println(dd.getCity().toString());
		for(int i = 0; i<dd.getEntityList().length; i++){
			System.out.println(dd.getEntityList()[i].getName() + "/" + dd.getEntityList()[i].getType() + " count: " + dd.getEntityList()[i].getCount());
		}
	}

}
