package data;

import java.util.ArrayList;

public class DataDump {
	
	private City city;
	private ArrayList<Entity> entities;
	
	public DataDump(City city){
		this.setCity(city);
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity ent){
		entities.add(ent);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	

}
