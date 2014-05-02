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
		connector.init("localhost", 3306, "wikiDump", "root", "sner");
		
		fo = new FileOutput(false, "Crawlertexte.txt");
		
		System.out.println("erzeuge Crawlerunit!");
		
		String[] cities = {"Berlin", "Paris", "Hof", "Ney York", "Dheli", "Beijing", "Moskow", "Saint-Martin-du-Mont", "Aliquippa", "Daugai" };
		crawler = new CrawlerUnit(cities, 0, cities.length, connector, null, 1);
		
		System.out.println("Starte Crawling");
		
	}
	
	
	public void start(){
		fo.writeToFile(crawler.doIt());
		System.out.println("Fertig");
	}
	
	
	public static void main(String[] args) {
		LonLatParserTextcrawler test = new LonLatParserTextcrawler();
		test.init();
		test.start();
	}

}
