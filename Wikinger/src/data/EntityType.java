package data;

public class EntityType {

	private String name;
	private double weighting;
	private int id;
	
	public EntityType(int id, String name, double weighting) {
		this.setId(id);
		this.setName(name);
		this.setWeighting(weighting);
	}

	public EntityType(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
