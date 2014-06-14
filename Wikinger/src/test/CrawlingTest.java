package test;

import data.control.StanfordNER;
import part.log.Logger;
import part.offline.control.OfflineController;
import part.offline.control.Status;

public class CrawlingTest {

	public static void main(String[] args) {
		Status s;
		OfflineController c = new OfflineController(new StanfordNER("./classifiers/english.muc.7class.distsim.crf.ser.gz"), "./gazetteer.csv");
		try {
			s = c.init(1, "localhost", 3306, "wiki", "root", "");
			Logger lg = new Logger("./log.txt", s);
			Thread t = new Thread(lg);
			t.start();
			c.startCrawling();
			c.startWritingToDatabase("wikinger2", "./temp/");
			c.createInverseDocFrequency("wikinger2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
