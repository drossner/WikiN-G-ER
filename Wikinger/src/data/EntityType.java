package data;

public class EntityType {

	private String name;
	private double weighting;
	
	public EntityType(String name, double weighting) {
		this.setName(name);
		this.setWeighting(weighting);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeighting() {
		return weighting;
	}

	public void setWeighting(double weighting) {
		this.weighting = weighting;
	}
}
