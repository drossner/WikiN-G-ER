package part.online.control;

import java.sql.SQLException;
import java.util.ArrayList;

import data.City;
import data.Entity;
import data.database.connection.WikiDBSQLConnector;

public class WeightingUnit extends Thread {

	private int start;
	private int end;
	private Entity[] entities;
	private WikiDBSQLConnector connector;
	private City[] resultCities;

	public WeightingUnit(int start, int end, Entity[] entities,	WikiDBSQLConnector connector) {
		this.start = start;
		this.end = end;
		this.entities = entities;
		this.connector = connector;
	}

	public void run() {
		ArrayList<City> temp = new ArrayList<City>();
		Entity entity;
		City[] cities;

		try {
			for (int i = start; i <= end; i++) {
				entity = connector.getEntity(entities[i].getName(), entities[i].getType());
				cities = connector.getCities(entity.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultCities = temp.toArray(new City[temp.size()]);
	}

	public City[] getResultCities() {
		return resultCities;
	}

	public void setResultCities(City[] resultCities) {
		this.resultCities = resultCities;
	}
}
