package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataDump{
	
	private City city;
	private Entity[] entityList;
	
	public DataDump(City city, ArrayList<Entity> entities){
		this.setCity(city);
		extractEntities(entities);
	}

	/**
	 * muss getestet werden!
	 * @param entities
	 */
	private void extractEntities(ArrayList<Entity> entities) {
		HashMap<String, Entity> temp = new HashMap<String, Entity>();	
		Entity entity = null;
		int count;
		
		for(Entity ent : entities){
			if(temp.containsKey(ent.getName()) && ent.getType() == temp.get(ent.getName()).getType()){
				entity = temp.get(ent.getName());
				count = entity.getCount();
				ent.setCount(++count);
				temp.remove(ent.getName());
				temp.put(ent.getName(), ent);
			}else{
				temp.put(ent.getName(), ent);
			}
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

}
