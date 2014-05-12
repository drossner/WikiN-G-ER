package part.offline.control;

import data.City;
import data.Entity;
import data.control.FileInput;

public class DBWriterUnit extends Thread{
	
	private String fileName;
	private String entitySplitSymbol;
	private String coordsSplitSymbol;
	private DBSQLConnector connector;
	private FileInput in;
	
	public DBWriterUnit(String fileName, DBSQLConnector connector, String entitySplitSymbol, String coordsSplitSymbol) {
		this.connector = connector;
		this.coordsSplitSymbol = coordsSplitSymbol;
		this.entitySplitSymbol = entitySplitSymbol;
		this.fileName = fileName;
		
		try {
			this.in = new FileInput(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		String[] city;
		String[] entities;		
		Entity temp;
		int cityID, entityID;
		String[] dataArr = in.loadCompleteFile();
		
		for (int i = 0; i < dataArr.length; i++) {
			entities = dataArr[i].split(entitySplitSymbol);
			city = entities[0].split(coordsSplitSymbol);
			
			cityID = connector.writeCity(new City(city[0], Double.parseDouble(city[1]), Double.parseDouble(city[2])));
			
			for (int j = 1; j < entities.length; j++) {
				temp = new Entity(entities[j], entities[j++], entities[j++]);
				entityID = connector.writeEntity(temp);
				
				connector.writeConnection(cityID, entityID, temp.getCount());
			}
		}
	}

}
