package part.online.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import data.City;
import data.Entity;
import data.EntityType;
import data.database.connection.WikiNerConnector;
import data.database.connection.WikiNerGraphConnector;

public class WeightingSystem {

	private EntityType[] entitiesWeighting;

	public WeightingSystem(EntityType[] entitiesWeighting) {
		this.entitiesWeighting = entitiesWeighting;
	}

	public City[] calculateCity(Entity[] entities, String dbDir) {
		WikiNerGraphConnector connector = WikiNerGraphConnector.getInstance(dbDir);
		WeightingUnit unit;
		double maxWeight = 0;
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<City> resultCities = new ArrayList<City>();
		Map<String, EntityType> entityTypes = new HashMap<String, EntityType>();
		int start = 0;
		int step = 15;

		for (int i = 0; i < entitiesWeighting.length; i++) {
			if (maxWeight < Math.abs(entitiesWeighting[i].getWeighting()))
				maxWeight = Math.abs(entitiesWeighting[i].getWeighting());
		}

		for (int i = 0; i < entitiesWeighting.length; i++) {
			entitiesWeighting[i].setWeighting(entitiesWeighting[i].getWeighting() / maxWeight);
			entityTypes.put(entitiesWeighting[i].getName(), entitiesWeighting[i]);
		}

		while (start < entities.length) {
			unit = new WeightingUnit(start, start + step, entities, connector,
					resultCities, entityTypes);
			start += step;
			executor.execute(unit);
		}
		executor.shutdown();

		try {
			executor.awaitTermination(200, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long st = System.currentTimeMillis();
		resultCities = packArray(resultCities);
		System.out.println("Pack: "+(System.currentTimeMillis()-st));
		//Collections.sort(resultCities); ersetzt durch ausgeben der top 10
		
		return top10(resultCities);
	}

	private City[] top10(ArrayList<City> resultCities) {
		//testZeit
		long start = System.currentTimeMillis();
		City[] rc;
		
		int collectionSize = resultCities.size();
		if(collectionSize >= 10){
			rc = new City[10];
		} else {
			rc = new City[collectionSize];
		}
		
		for(int i = 0; i < rc.length; i++){
			double max = resultCities.get(0).getScore();
			int maxInd = 0;
			for (int j = 1; j < resultCities.size(); j++) {
				if(resultCities.get(j).getScore() > max){
					max = resultCities.get(j).getScore();
					maxInd = j;
				}
			}
			rc[i] = resultCities.get(maxInd);
			resultCities.remove(maxInd);
		}
		
		System.out.println("Top 10: "+(System.currentTimeMillis() - start));
		return rc;
	}

	private ArrayList<City> packArray(ArrayList<City> resultCities) {
		HashMap<String, City> hm = new HashMap<String, City>();
		StringBuilder hashKey = new StringBuilder();

		for (Iterator<City> iterator = resultCities.iterator(); iterator.hasNext();) {
			City temp;
			City city = iterator.next();

			hashKey.append(city.getName());
			hashKey.append("/");
			hashKey.append(city.getLati());
			hashKey.append("/");
			hashKey.append(city.getLongi());

			if (hm.containsKey(hashKey.toString())) {
				temp = hm.get(hashKey.toString());
				double score = temp.getScore();
				temp.setScore(score + city.getScore());
				temp.setCounter(temp.getCounter() + 1);
			} else {
				city.setCounter(1);
				hm.put(hashKey.toString(), city);
			}

			hashKey = new StringBuilder();
		}

		ArrayList<City> cityArr = new ArrayList<City>();
		Iterator<City> iterator = hm.values().iterator();
		while (iterator.hasNext()) {
			City city = iterator.next();
			city.setScore(city.getScore() / city.getCounter());
			cityArr.add(city);
		}

		return cityArr;
	}

}
