package part.offline.control;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.City;
import data.control.StanfordNER;

public class CrawlerUnit implements Runnable{
	String[] cities;
	int start, end;
	int id;
	SQLConnector connector;
	StanfordNER ner;
	
	public CrawlerUnit(String[] cities, int start, int end, SQLConnector connector, StanfordNER ner, int id){
		this.cities = cities;
		this.start = start;
		this.end = end;
		this.connector = connector;
		this.ner = ner;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = this.start; i <= this.end ; i++) {
			System.out.println(cities[i]+" i:"+i);
			CityCreator cc = new CityCreator(ner, this.cities[i]);
			int[] pageIDs = connector.getPageIDs(cities[i], id);
			
			int[] revIDs = null;
			if(pageIDs.length>0){
				revIDs = filter(connector.getRevIDs(pageIDs));
			}
			
			if(revIDs != null){
			String[] text = connector.getTexts(revIDs);
			ArrayList<City> cityList = new ArrayList<City>();
			for (int j = 0; j < text.length; j++) {
				City city = cc.getCities(text[j]);
				if(city != null){
					cityList.add(city);
					System.out.println(city.getName()+" wurde dem Gazetteer hinzugefügt");
				}
			}
			if(cityList.size()>0);//in datei schreiben!
			
			}
		}
	}
	
	/**
	 * deletes multiple rev_ids
	 * @param bilder
	 * @return
	 */
    public int[] filter(int[] bilder) {
        Set<Integer> temp = new TreeSet<Integer>();
 
        for(int i : bilder){
                temp.add(i);
        }
        int[] result = new int[temp.size()];
        int index = 0; 
        for(Integer i : temp) {
           result[index++] = i;
        } 
        return result;
    }

}
