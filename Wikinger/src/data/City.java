package data;

public class City {
	
	private String name;
	private double longi;
	private double lati;
	private double score;
	
	public City(String name, double latitude, double longitude){
		setName(name);
		setLongi(longitude);
		setLati(latitude);
	}
	
	public City(String name, String latitude, String longitude) {
		setName(name);
		setLati(Double.parseDouble(latitude));
		setLongi(Double.parseDouble(longitude));
	}

	public String cityToString(){
		return getName() + "; " + getLati() + ";" + getLongi();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}

	public double getLati() {
		return lati;
	}

	public void setLati(double lati) {
		this.lati = lati;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
