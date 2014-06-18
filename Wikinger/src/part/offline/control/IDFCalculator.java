package part.offline.control;

import java.sql.SQLException;

import data.database.connection.WikiNerConnector;

public class IDFCalculator extends Thread{

	private WikiNerConnector connector;
	private int[] entities;
	private int countCities;
	private int start;
	private int ende;

	public IDFCalculator(WikiNerConnector connector, int[] entities, int countCities, int start, int ende) {
		this.connector = connector;
		this.entities = entities;
		this.countCities = countCities;
		this.start = start;
		this.ende = ende;
	}
	
	public void run(){
		int countEntity;
		double idf;
		
		try {			
			for (int i = start; i < ende; i++) {
				countEntity = connector.getEntityConnCount(entities[i]);
				idf = Math.log((countCities / countEntity));
				connector.setEntityIDF(idf, entities[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
