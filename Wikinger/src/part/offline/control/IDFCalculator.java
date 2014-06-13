package part.offline.control;

import java.sql.SQLException;

import data.database.connection.WikiNerConnector;

public class IDFCalculator {

	private WikiNerConnector connector;

	public IDFCalculator(WikiNerConnector connector) {
		this.connector = connector;
	}
	
	public void calculate(){
		int[] entities;
		int counter;
		double idf;
		
		try {
			entities = connector.getAllEntityIDs();
			
			for (int i = 0; i < entities.length; i++) {
				counter = connector.getEntityCounter(entities[i]);
				idf = Math.log(1.0 + (entities.length/counter));
				connector.setEntityIDF(idf, entities[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
