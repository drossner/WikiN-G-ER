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
	private int id;
	private Status status;
	
	public DBWriterUnit(int id, String fileName, DBSQLConnector connector, String entitySplitSymbol, String coordsSplitSymbol, Status status) {
		this.connector = connector;
		this.coordsSplitSymbol = coordsSplitSymbol;
		this.entitySplitSymbol = entitySplitSymbol;
		this.setFileName(fileName);
		this.id = id;
		this.status = status;
		
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
			status.setWorkForEachDone(i, id);
			System.out.println("Thread: " + id + " bei " + i);
			entities = dataArr[i].split(entitySplitSymbol);
			city = entities[0].split(coordsSplitSymbol);
			
			cityID = connector.writeCity(new City(city[0], city[1], city[2]));
			
			for (int j = 1; j < entities.length; j++) {
				temp = new Entity(entities[j], entities[++j], entities[++j]);
				entityID = connector.writeEntity(temp);
				
				connector.writeConnection(cityID, entityID, temp.getCount());
			}
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
