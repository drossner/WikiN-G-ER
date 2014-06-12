package test;

import java.sql.SQLException;

import data.control.StanfordNER;
import part.log.Logger;
import part.offline.control.OfflineController;
import part.offline.control.Status;

public class CrawlingTest {

	public static void main(String[] args) {
		Status s;
		OfflineController c = new OfflineController(new StanfordNER("./classifiers/english.muc.7class.distsim.crf.ser.gz"), "./gazetteer.csv");
		try {
			s = c.init(1, "172.16.50.83", 3306, "wiki", "student", "aisTM14Wiki");
			Logger lg = new Logger("./log.txt", s);
			Thread t = new Thread(lg);
			t.start();
			c.startCrawling();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
