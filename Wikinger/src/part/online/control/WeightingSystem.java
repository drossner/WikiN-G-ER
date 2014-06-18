package part.online.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.City;
import data.Entity;
import data.EntityType;
import data.database.connection.WikiNerConnector;

public class WeightingSystem {

	private EntityType[] entitiesWeighting;

	public WeightingSystem(EntityType[] entitiesWeighting) {
		this.entitiesWeighting = entitiesWeighting;
	}
	
	public City[] calculateCity(Entity[] entities, String host, int port, String database, String user, String passwd){
		WikiNerConnector connector = new WikiNerConnector();
		WeightingUnit unit;
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<City> resultCities = new ArrayList<City>();
		int start = 0;
		double max = 0.0;
		
		for (int i = 0; i < entitiesWeighting.length; i++) {
			if(entitiesWeighting[i].getWeighting() > max){
				max = entitiesWeighting[i].getWeighting();
			}
		}
		
		for (int i = 0; i < entitiesWeighting.length; i++) {
			entitiesWeighting[i].setWeighting(entitiesWeighting[i].getWeighting() / max);
		}
		
		connector.init(host, port, database, user, passwd);
		unit = new WeightingUnit(0, 5, entities, connector, resultCities, entitiesWeighting);
		
		while(start < entities.length){
			unit.setStart(start);
			unit.setEnd(start + 100);
			executor.execute(unit);
			start += 100;
		}
		
		executor.shutdown();
		
		Collections.sort(resultCities);
		
		return resultCities.toArray(new City[resultCities.size()]);
	}

}
