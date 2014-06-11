package part.offline.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import data.DataDump;
import data.Entity;
import data.control.FileOutput;
import data.control.StanfordNER;
import data.database.connection.SQLConnector;

public class CrawlerUnit implements Runnable {
	private int[] textIDs;
	private int start, end, acPos;
	private int id;
	private SQLConnector connector;
	private StanfordNER ner;
	private FileOutput out;
	private int maxLength = 0;
	private String splitSymbol = ";#/";
	private Status status;
	
	
	public CrawlerUnit(int[] textIDs, int start, int end, SQLConnector connector, StanfordNER ner, int id, FileOutput out, Status status){
		this.textIDs = textIDs;
		this.start = start;
		this.end = end;
		this.connector = connector;
		this.ner = ner;
		this.setId(id);
		this.out = out;
		this.status = status;
	}

	public void run() {
		LatitudeLongitudeParser llp = new LatitudeLongitudeParser();
		StringBuffer temp = new StringBuffer();
		CityCreator cc = new CityCreator(ner, llp);
		String text;
		DataDump dump;
		int revID;
		String pageTitle;
		StringBuilder builder = new StringBuilder();
		
		for (int i = this.start; i <= this.end ; i++) {
			status.setWorkForEachDone(i - start, id);
			
			try {
				text = connector.getText(this.textIDs[i]);
				dump = cc.getCity(text);
				
				if(dump != null){
					revID = connector.getRevID(this.textIDs[i]);
					pageTitle = connector.getPageTitle(revID);
					
					dump.getCity().setName(pageTitle);
					
					builder.append(dump.getCity().cityToString());
					entitesToString(dump, builder);
					
					out.writeToFile(builder);
					
					builder = new StringBuilder();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			setAcPos(end - start + i);
		}
	}

	private void entitesToString(DataDump dump, StringBuilder temp) {
		for (Entity ent : dump.getEntityList()) {
			temp.append(splitSymbol);
			temp.append(ent.getName());
			temp.append(splitSymbol);
			temp.append(ent.getType());
			temp.append(splitSymbol);
			temp.append(ent.getCount());
		}
	}

	/**
	 * deletes multiple rev_ids
	 * 
	 * @param bilder
	 * @return
	 */
	public int[] filter(int[] bilder) {
		Set<Integer> temp = new TreeSet<Integer>();

		for (int i : bilder) {
			temp.add(i);
		}
		int[] result = new int[temp.size()];
		int index = 0;
		for (Integer i : temp) {
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
