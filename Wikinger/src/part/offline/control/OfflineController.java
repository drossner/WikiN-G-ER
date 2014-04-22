package part.offline.control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

import data.control.StanfordNER;

public class OfflineController {
	
	String[] uniqueCityNames;
	int cities;
	StanfordNER ner;
	
	public OfflineController(StanfordNER ner){
		ArrayList<String> temp = new ArrayList<String>(gaz.loadGazetteerforCrawler());
		uniqueCityNames = new String[temp.size()];
		temp.toArray(uniqueCityNames);
		cities = uniqueCityNames.length;
		this.ner = ner;
	}
	
	/**
	 * Method starts crawling with given parameters on a wiki MySQL-database
	 * @param threads Threads which are crawling
	 * @param host Hostaddress of the database
	 * @param port Port of the database
	 * @param database databasename
	 * @param user user
	 * @param passwd password
	 */
	public void startCrawling(int threads, String host, int port, String database, String user, String passwd){
		Thread[] threadList = new Thread[threads];
		SQLConnector[] connectors = new SQLConnector[threads];
//		Connector connector = new Connector();
//		connector.init(host, port, database, user, passwd, threads);
		
		
		
		for (int i = 0; i < connectors.length; i++) {
			connectors[i] = new SQLConnector();
			connectors[i].init(host, port, database, user, passwd);
		}
		
		int step = cities/threads;
		int counter = 0;
		int rest = cities%threads;
		
			for (int i = 0; i < threads-1; i++) {
				CrawlerUnit temp = new CrawlerUnit(uniqueCityNames, counter, counter+step-1, gaz, connectors[i], ner, i);
				threadList[i] = new Thread(temp);
				threadList[i].start();
				counter += step;	
			}
			
			CrawlerUnit temp = new CrawlerUnit(uniqueCityNames, counter, counter+step-1+rest, gaz, connectors[threads-1], ner, threads-1);
			threadList[threads-1] = new Thread(temp);
			threadList[threads-1].start();
			
			
		for (int i = 0; i < threadList.length; i++) {
			try {
				threadList[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
