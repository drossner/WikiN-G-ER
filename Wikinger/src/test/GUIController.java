package test;

import java.sql.SQLException;

import data.control.StanfordNER;
import part.offline.constants.DBInformations;
import part.offline.control.OfflineController;
import part.offline.control.Status;

public class GUIController implements Runnable {
	
	OfflineController off;
	String[] infos;
	
	public GUIController() {
		
	}
	
	
	public Status startOfflineController(String[] infos) {
		off = new OfflineController(new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz"), "./gazetteer.csv");
		this.infos = infos;
		return off.init(Integer.parseInt(infos[DBInformations.MAX_THREADS]));
	}
	
	public void startCrawling(){
		try {
			off.startCrawling(infos[DBInformations.HOSTNAME], Integer.parseInt(infos[DBInformations.PORT]), 
					infos[DBInformations.DB_NAME], infos[DBInformations.USERNAME], infos[DBInformations.PASSWD]);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		startCrawling();
	}
	
	

}
