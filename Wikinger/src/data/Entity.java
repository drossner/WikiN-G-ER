package data;

public class Entity {
	
	private String name;
	private int count;
	private String type;
	
	public Entity(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public String getType(){
		return type;
	}

	@Override
	public String toString(){
		return "Entity [name=" + name + ", type=" + type + "]";
	}

	public void setType(String type){
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
