package part.offline.control;

import data.City;
import data.control.StanfordNER;

public class CityCreator {
	StanfordNER ner;
	String name;
	LonLatParser llp;
	
	public CityCreator(StanfordNER ner, String name){
		this.ner = ner;
		this.name = name;
		this.llp = new LonLatParser();
	}
	
	/**
	 * Method creates a City object of a given wikidump text, with lon, lat and entities
	 * @param text text extracted of the wikidump
	 * @return returns a City-object or null if the given text is not a city
	 */
	public City getCities(String text){
		if(!checkIfCity(text)) return null;
		System.out.println("CityCreator: "+this.name);
		double[] lonlat = llp.getLonLat(text);
		if(lonlat == null) return null;
		double lon = lonlat[0]+(lonlat[1]*60.0+lonlat[2])/3600.0;
		double lat = lonlat[3]+(lonlat[4]*60.0+lonlat[5])/3600.0;
		
		//CrawlerOutput cop = new CrawlerOutput(name, text, lon, lat);
		//bereits programmieren
		//return ner.extractEntities(cop);
		return null;
	}

	
	/**
	 * Check if a given text represents a city
	 * @param text text extracted of the wikidump
	 * @return true if text represents a city
	 */
	public boolean checkIfCity(String text) {
		return text.contains("|population");
		
	}
	

}
