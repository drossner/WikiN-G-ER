package test;

import java.nio.charset.Charset;

import part.offline.control.OfflineController;
import part.offline.control.Status;
import data.control.StanfordNER;

public class DBSQLConnectorTest {
	
	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset());
		OfflineController off = new OfflineController(new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz"), "./gazetteer.csv");
		Status s = off.init(8, "localhost", 3306, "wikinger", "root", "");
		System.out.println("Start writing");
		off.startWritingToDatabase("localhost", 3306, "wikinger", "root", "", "./crawleroutput/");
		System.out.println("fertig");
	}

}
