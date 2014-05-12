package part.offline.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.City;
import data.DataDump;
import data.Entity;
import data.control.StanfordNER;

public class CityCreator {
	StanfordNER ner;
	String name;
	LatitudeLongitudeParser llp;
	
	public CityCreator(StanfordNER ner, String name, LatitudeLongitudeParser llp){
		this.ner = ner;
		this.name = name;
		this.llp = llp;
	}
	
	/**
	 * Method creates a City object of a given wikidump text, with lon, lat and entities
	 * @param text text extracted of the wikidump
	 * @return returns a City-object or null if the given text is not a city
	 */
	public DataDump getCity(String text){
		ArrayList<Entity> temp = null;
		double[] coords;
		City city;
		DataDump rc = null;
		
		if(!checkIfCity(text)) return null;
	
		try {
			temp = ner.extractEntities(new StringBuffer(text));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		coords = llp.parseLatLon(text);
		
		if(coords == null){
			System.out.println(name);
		}else if((coords[0] == 0.0 && coords[1] == 0.0)){
			System.out.println(name + ": " + coords[0] + "; " + coords[1]);
		}
		
		if(coords != null){
			city = new City(name, coords[0], coords[1]);
			rc = new DataDump(city, temp);
		}
		
		return rc;
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
