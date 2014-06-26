package part.offline.control;

import org.neo4j.graphdb.Node;

import data.City;
import data.Entity;
import data.control.FileInput;
import data.database.connection.WikiNerConnector;
import data.database.connection.WikiNerGraphConnector;

public class DBWriterUnit extends Thread{
	
	private String fileName;
	private String entitySplitSymbol;
	private String coordsSplitSymbol;
	private WikiNerGraphConnector connector;
	private FileInput in;
	private int id;
	private Status status;
	
	public DBWriterUnit(int id, String fileName, WikiNerGraphConnector connector, String entitySplitSymbol, String coordsSplitSymbol) {
		this.connector = connector;
		this.coordsSplitSymbol = coordsSplitSymbol;
		this.entitySplitSymbol = entitySplitSymbol;
		this.setFileName(fileName);
		this.id = id;
		//this.status = status;
		
		try {
			this.in = new FileInput(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		String[] cityArr;
		String[] entities;		
		Entity temp;
		Node city, entity;
		String[] dataArr = in.loadCompleteFile();
		
		for (int i = 0; i < dataArr.length; i++) {
			System.out.println(i + " / " + dataArr.length);
			//status.setWorkForEachDone(i, id);
			try{
				entities = dataArr[i].split(entitySplitSymbol);
				cityArr = entities[0].split(coordsSplitSymbol);
			
				city = connector.createCity(new City(cityArr[0], cityArr[1], cityArr[2]));
				
				for (int j = 1; j < entities.length; j++) {
					System.out.println("Entities" + j + " / " + entities.length);
					temp = new Entity(entities[j], entities[++j], entities[++j]);
					entity = connector.createEntity(temp);
				
					connector.createConnection(city, entity, temp.getCount());
				}
		
			}catch(Exception e){
				e.printStackTrace();
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
