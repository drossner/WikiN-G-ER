package part.online.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
		EntityType[] temp = null;
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<City> resultCities = new ArrayList<City>();
		Map<String, EntityType> entityTypes = new HashMap<String, EntityType>();
		int start = 0;
		int step = 10;

		connector.init(host, port, database, user, passwd);
		try {
			temp = connector.getEntityTypes();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < entitiesWeighting.length; j++) {
				if(temp[i].getName().equals(entitiesWeighting[j].getName())){
					temp[i].setWeighting(entitiesWeighting[i].getWeighting());
					break;
				}
			}
			entityTypes.put(temp[i].getName(), temp[i]);
		}
		
		while (start < entities.length){
			unit = new WeightingUnit(start, start + step, entities, connector, resultCities, entityTypes);
			start += step;
			executor.execute(unit);
			connector = new WikiNerConnector();
			connector.init(host, port, database, user, passwd);
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(100, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Collections.sort(resultCities);
		
		return resultCities.toArray(new City[resultCities.size()]);
	}

}
