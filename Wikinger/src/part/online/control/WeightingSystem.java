package part.online.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.City;
import data.Entity;
import data.database.connection.WikiNerConnector;

public class WeightingSystem {

	private int[] entitiesWeighting;

	public WeightingSystem(int[] entitiesWeighting) {
		this.entitiesWeighting = entitiesWeighting;
	}
	
	public City[] calculateCity(Entity[] entities, String host, int port, String database, String user, String passwd){
		WikiNerConnector connector = new WikiNerConnector();
		WeightingUnit unit;
		ExecutorService executor = Executors.newCachedThreadPool();
		int start = 0;
		
		connector.init(host, port, database, user, passwd);
		unit = new WeightingUnit(0, 5, entities, connector);
		
		while(start < entities.length){
			unit.setStart(start);
			unit.setEnd(start + 100);
			executor.execute(unit);
			start += 100;
		}
		
		return null;
	}

}
