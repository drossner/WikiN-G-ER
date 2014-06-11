package part.online.control;

import data.City;
import data.Entity;

public class WeightingSystem {

	private int[] entitiesWeighting;

	public WeightingSystem(int[] entitiesWeighting) {
		this.entitiesWeighting = entitiesWeighting;
	}
	
	public City[] calculateCity(Entity[] entities, int threadCount){
		WeightingUnit[] units = new WeightingUnit[threadCount];
		
		return null;
	}

}
