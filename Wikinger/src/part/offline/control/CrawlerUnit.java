package part.offline.control;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import data.DataDump;
import data.Entity;
import data.control.FileOutput;
import data.control.StanfordNER;

public class CrawlerUnit implements Runnable{
	private String[] cities;
	private int start, end, acPos;
	private int id;
	private SQLConnector connector;
	private StanfordNER ner;
	private FileOutput out;
	
	
	public CrawlerUnit(String[] cities, int start, int end, SQLConnector connector, StanfordNER ner, int id, FileOutput out){
		this.cities = cities;
		this.start = start;
		this.end = end;
		this.connector = connector;
		this.ner = ner;
		this.setId(id);
		this.out = out;
	}

	public void run() {
		LatitudeLongitudeParser llp = new LatitudeLongitudeParser();
		StringBuffer temp = new StringBuffer();
		for (int i = this.start; i <= this.end ; i++) {
			System.out.println(cities[i]+" i:"+i);
			CityCreator cc = new CityCreator(ner, this.cities[i], llp);
			int[] pageIDs = connector.getPageIDs(cities[i]);
			
			int[] revIDs = null;
			if(pageIDs.length>0){
				revIDs = filter(connector.getRevIDs(pageIDs));
			}
			
			if(revIDs != null){
			String[] text = connector.getTexts(revIDs);
			ArrayList<DataDump> dumpList = new ArrayList<DataDump>();
			for (int j = 0; j < text.length; j++) {
				DataDump dump = cc.getCity(text[j]);
				if(dump != null){
					dumpList.add(dump);
					System.out.println(dump.getCity().getName()+" wurde der CityList hinzugefügt");
				}
			}
			if(dumpList.size()>0){
				String[] writeList = new String[dumpList.size()];
				int c = 0;
				for(DataDump dump : dumpList){
					temp.append(dump.getCity().cityToString());
					temp.append(entitesToString(dump, temp));
					writeList[c] = temp.toString();
					c++;
				}
				out.writeToFile(writeList);
			}
			
			}
			setAcPos(end - start + i);
		}
	}
	
	private String entitesToString(DataDump dump, StringBuffer temp) {
		for(Entity ent : dump.getEntityList()){
			temp.append(";");
			temp.append(ent.getName());
			temp.append(";");
			temp.append(ent.getType());
			temp.append(";");
			temp.append(ent.getCount());
		}
		temp.append(";");
		return temp.toString();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcPos() {
		return acPos;
	}

	public void setAcPos(int acPos) {
		this.acPos = acPos;
	}

}
