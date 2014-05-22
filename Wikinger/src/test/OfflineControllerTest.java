package test;

import data.control.StanfordNER;
import part.offline.control.OfflineController;

public class OfflineControllerTest {

	public static void main(String[] args) {
		OfflineController off = new OfflineController(new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz"), "./gazetteer.csv");
		System.out.println("Init Crawling");
		off.init();
		System.out.println("Start Crawling");
		//off.startCrawling(1, "localhost", 3306, "wiki", "root", "");
		System.out.println("Start writing");
		off.startWritingToDatabase("localhost", 3306, "wikinger2", "root", "", "./crawleroutputs/");
		System.out.println("fertig");
	}

}
