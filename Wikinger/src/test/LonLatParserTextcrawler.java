package test;

import data.control.FileOutput;
import part.offline.control.CrawlerUnit;
import part.offline.control.SQLConnector;

public class LonLatParserTextcrawler {
	
	SQLConnector connector;
	CrawlerUnit crawler;
	FileOutput fo;
	
	public void init(){
		connector = new SQLConnector();
		connector.init("localhost", 3306, "wiki", "root", "");
		
		fo = new FileOutput(false, "Crawlertexte");
		
		String[] cities = {"Berlin", "Paris", "Hof", "Ney York", "Dheli", "Beijing", "Moskow", };
		crawler = new CrawlerUnit(cities, 0, cities.length, connector, null, 1);
	}
	
	
	public static void main(String[] args) {
		LonLatParserTextcrawler test = new LonLatParserTextcrawler();
		test.init();
		
	}

}
