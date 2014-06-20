package part.online.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.City;
import data.Entity;
import data.EntityType;
import data.database.connection.WikiNerConnector;

public class WeightingUnit extends Thread {

	private int start;
	private int end;
	private Entity[] entities;
	private WikiNerConnector connector;
	private ArrayList<City> resultCities;
	private EntityType[] entityWeighting;

	public WeightingUnit(int start, int end, Entity[] entities,
			WikiNerConnector connector, ArrayList<City> resultCities,
			EntityType[] entitiesWeighting) {
		this.start = start;
		this.end = end;
		this.entities = entities;
		this.connector = connector;
		this.resultCities = resultCities;
		this.entityWeighting = entitiesWeighting;
	}

	public void run() {
		Entity entity;
		HashMap<String, City> cities = new HashMap<String, City>();
		City[] cityArr = null;
		int counter;
		int maximumEntity;
		double score;
		City temp;
		Iterator<City> it;
		EntityType et = null;

		try {
			for (int i = start; i <= end && i < entities.length; i++) {
				entity = connector.getEntity(entities[i].getName(),
						entities[i].getType());
				if (entity == null) {
					System.out.println("null bei : " + entities[i].getName()+ " " + entities[i].getType());
				} else {
					cityArr = connector.getCities(entity.getId());
					for (int j = 0; j < entityWeighting.length; j++) {
						if (entityWeighting[j].getName().equals(entities[i].getType().toUpperCase())) {
							et = entityWeighting[j];
							break;
						}
					}
					for (int j = 0; j < cityArr.length; j++) {
						counter = connector.getCityEntityCounter(cityArr[j].getName(), entity.getId());
						maximumEntity = connector.getMaxEntity(cityArr[j].getName());
						score = et.getWeighting() 
								* Math.log(entity.getCount())
								* ((counter * 1.0) / maximumEntity)
								* entity.getIdf();
						cityArr[j].setScore(score);
						addToHashMap(cities, cityArr[j]);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		it = cities.values().iterator();
		while (it.hasNext()) {
			
			temp = it.next();
			temp.setScore(temp.getScore() / temp.getCounter());
			resultCities.add(temp);
			System.out.println("füge hinzu: " + temp.getName());
		}
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
			score = score + city.getScore(); // Hier müssen wir vll noch etwas
												// verändern!
			counter = cityTemp.getCounter();
			cityTemp.setCounter(++counter);
			cityTemp.setScore(score);
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
