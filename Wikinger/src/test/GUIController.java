package test;

import data.control.StanfordNER;
import part.offline.constants.DBInformations;
import part.offline.control.OfflineController;
import part.offline.control.Status;

public class GUIController {
	
	OfflineController off;
	
	public GUIController() {
		
	}
	
	
	public Status startOfflineController(String[] infos) {
		off = new OfflineController(new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz"), "./gazetteer.csv");
		return off.init(Integer.parseInt(infos[DBInformations.MAX_THREADS]));
	}
	
	public void startCrawling(String[] infos){
		off.startCrawling(infos[DBInformations.HOSTNAME], Integer.parseInt(infos[DBInformations.PORT]), 
				infos[DBInformations.DB_NAME], infos[DBInformations.USERNAME], infos[DBInformations.PASSWD]);
	}
	
	

}
