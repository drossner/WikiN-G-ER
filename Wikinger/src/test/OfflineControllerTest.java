package test;

import java.nio.charset.Charset;

import data.control.StanfordNER;
import part.offline.control.OfflineController;
import part.offline.control.Status;

public class OfflineControllerTest {

	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset());
		OfflineController off = new OfflineController(new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz"), "./gazetteer.csv");
		System.out.println("Init Crawling");
		Status s = off.init(8);
		System.out.println("Start Crawling");
		OfflineControllerTestGui gui = new OfflineControllerTestGui(s);
		gui.init();
		off.startCrawling("localhost", 3306, "wiki", "root", "");
	}

}
