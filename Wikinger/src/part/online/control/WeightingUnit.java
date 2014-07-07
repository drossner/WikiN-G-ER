package part.online.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.City;
import data.DataDump;
import data.Entity;
import data.EntityType;
import data.database.connection.WikiNerGraphConnector;

public class WeightingUnit extends Thread {

	private int start;
	private int end;
	private Entity[] entities;
	private WikiNerGraphConnector connector;
	private ArrayList<City> resultCities;
	private Map<String, EntityType> entityWeighting;

	public WeightingUnit(int start, int end, Entity[] entities,
			WikiNerGraphConnector connector, ArrayList<City> resultCities,
			Map<String, EntityType> entitiesWeighting) {
		this.start = start;
		this.end = end;
		this.entities = entities;
		this.connector = connector;
		this.resultCities = resultCities;
		this.entityWeighting = entitiesWeighting;
	}

	public void run() {
		HashMap<String, City> cities = new HashMap<String, City>();
		DataDump[] dpArr;
		int maximumEntity = 0;
		double score;
		City temp;
		Iterator<City> it;
		EntityType et = null;

		for (int i = start; i <= end && i < entities.length; i++) {
			et = entityWeighting.get( entities[i].getType());
			dpArr = connector.getDataEntity(entities[i]);
			
			if (dpArr != null && et.getWeighting() != 0.0) {
				for (int j = 0; j < dpArr.length; j++) {
					if (dpArr[j].getCounter() > maximumEntity)
						maximumEntity = dpArr[j].getCounter();
				}

				for (int j = 0; j < dpArr.length; j++) {
					score = et.getWeighting() * ((entities[i].getCount() * 1.0) / entities.length) * dpArr[j].getCounter() * dpArr[j].getIdf();
//					if(et.getWeighting() != 0.0){
//						System.out.println(et.getName() + ": " + et.getWeighting() + " , " + maximumEntity + " , " + dpArr[j].getIdf() + " , " + dpArr[j].getCounter() + " , " + entities[i].getCount());
//						System.out.println(score);
//					}
					dpArr[j].getCity().setScore(score);
					addToHashMap(cities, dpArr[j].getCity());
				}
			}
		}
		it = cities.values().iterator();;
		while (it.hasNext()) {
			
			temp = it.next();
			if(Double.isInfinite(temp.getScore() / temp.getCounter())) System.out.println(temp.getScore() + " , " + temp.getCounter());
			temp.setScore(temp.getScore() / temp.getCounter());
			synchronized(resultCities){
				resultCities.add(temp);
			}
		}
		System.out.println(Thread.class.getName() + " ist fertig mit rechnen !");
	}

	private void addToHashMap(HashMap<String, City> cities, City city) {
		StringBuilder hashMapKey = new StringBuilder();
		City cityTemp;
		double score;
		int counter;
		hashMapKey.append(city.getName() + "/");
		hashMapKey.append(city.getLati() + "/");
		hashMapKey.append(city.getLongi());
		if (cities.containsKey(hashMapKey.toString())) {
			cityTemp = cities.get(hashMapKey.toString());
			score = cityTemp.getScore();
			score += city.getScore(); 			// Hier müssen wir vll noch etwas
												// verändern!
			counter = cityTemp.getCounter();
			cityTemp.setCounter(++counter);
			cityTemp.setScore(score);
//			System.out.println(city.getName() + ": Score: " + score + " counter: " + counter);
		} else {
			cities.put(hashMapKey.toString(), city);
		}
	}

	public ArrayList<City> getResultCities() {
		return resultCities;
	}

	public void setResultCities(ArrayList<City> resultCities) {
		this.resultCities = resultCities;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return this.start;
	}

	public void setEnd(int ende) {
		this.end = ende;
	}
}
