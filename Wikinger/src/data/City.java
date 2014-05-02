package data;

public class City {
	
	private String name;
	private double longi;
	private double lati;
	
	public City(String name, double longi, double lati){
		setName(name);
		setLongi(longi);
		setLati(lati);
	}
	
	public String cityToString(){
		return getName() + "; " + getLongi() + ";" + getLati();
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

}
