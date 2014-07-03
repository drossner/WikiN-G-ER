package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataDump{
	
	private City city;
	private Entity[] entityList;
	private int counter;
	private double idf;
	
	public DataDump(City city, ArrayList<Entity> entities){
		this.setCity(city);
		extractEntities(entities);
	}
	
	public DataDump(ArrayList<Entity> entities){
		extractEntities(entities);
	}
	
	public DataDump(City city, int counter, double idf){
		this.city = city;
		this.setCounter(counter);
		this.setIdf(idf);
	}

	/**
	 * muss getestet werden!
	 * @param entities
	 */
	private void extractEntities(ArrayList<Entity> entities) {
		HashMap<String, Entity> temp = new HashMap<String, Entity>();	
		StringBuilder hashMapKey = new StringBuilder();
		Entity entity = null;
		int count;
		
		for(Entity ent : entities){
			hashMapKey.append(ent.getName() + "/" + ent.getType());
			if(temp.containsKey(hashMapKey.toString())){
				entity = temp.get(hashMapKey.toString());
				count = entity.getCount();
				ent.setCount(++count);
				temp.remove(hashMapKey.toString());
				temp.put(hashMapKey.toString(), ent);
			}else{
				temp.put(hashMapKey.toString(), ent);
			}
			hashMapKey = new StringBuilder();
		}
		entityList = temp.values().toArray(new Entity[temp.values().size()]);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Entity[] getEntityList() {
		return entityList;
	}

	public void setEntityList(Entity[] entityList) {
		this.entityList = entityList;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}

}
