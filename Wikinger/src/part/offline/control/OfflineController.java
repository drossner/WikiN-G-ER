package part.offline.control;
import java.io.File;
import java.sql.SQLException;

import part.offline.data.Gazetteer;
import data.control.FileOutput;
import data.control.StanfordNER;
import data.database.connection.WikiNerConnector;
import data.database.connection.WikipediaConnector;

public class OfflineController {
	
	private String[] uniqueCityNames;
	private int cityCount;
	private StanfordNER ner;
	private Gazetteer gaz;
	private Status status;
	private int threads;
	private String clf_path;
	
	public OfflineController(StanfordNER ner, String fileNameOldGazetteer){
		this.setGaz(new Gazetteer(fileNameOldGazetteer));
		this.ner = ner;
	}
	
	/**
	 * initialize the load of the old gazetteer
	 */
	public Status init(int threads){
		String [] uniqueCityNames = gaz.loadGazetter();
		this.uniqueCityNames = uniqueCityNames;
		setCityCount(uniqueCityNames.length);
		this.threads = threads;
		Status rc = new Status(threads, getCityCount()/threads);
		status = rc;
		return rc;
	}

	/**
	 * Method starts crawling with given parameters on a wiki MySQL-database
	 * @param threads Threads which are crawling
	 * @param host Hostaddress of the database
	 * @param port Port of the database
	 * @param database databasename
	 * @param user user
	 * @param passwd password
	 * @throws SQLException 
	 */
	public void startCrawling(String host, int port, String database, String user, String passwd) throws SQLException{
		Thread[] threadList = new Thread[threads];
		WikipediaConnector[] connectors = new WikipediaConnector[threads];
		int[] textIDs;
		
		for (int i = 0; i < connectors.length; i++) {
			connectors[i] = new WikipediaConnector();
			connectors[i].init(host, port, database, user, passwd);
		}
		
		textIDs = connectors[0].getAllText();
		
		int step = textIDs.length/threads;
		int counter = 0;
		int rest = textIDs.length%threads;
		
		for (int i = 0; i < threads-1; i++) {
			//System.out.println("Starte Thread " + i);
			CrawlerUnit temp = new CrawlerUnit(textIDs, counter, counter+step-1, connectors[i], ner, i, new FileOutput(true, "CrawlerOutPut" + i +".txt"), status);
			threadList[i] = new Thread(temp);
			threadList[i].start();
			counter += step;	
		}
		
		CrawlerUnit temp = new CrawlerUnit(textIDs, counter, counter+step-1+rest, connectors[threads-1], ner, threads-1, new FileOutput(true, "CrawlerOutPut" + (threads-1) +".txt"), status);
		threadList[threads-1] = new Thread(temp);
		threadList[threads-1].start();
			
		for (int i = 0; i < threadList.length; i++) {
			try {
				threadList[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void startWritingToDatabase(String host, int port, String database, String user, String passwd, String directory){
		int crawlerOutPutFileCount;
		Thread[] threadList;
		WikiNerConnector[] connectors;
		String fileDest;
		
		fileDest = directory + "CrawlerOutPut";
		crawlerOutPutFileCount = new File(directory).listFiles().length; 
		
		threadList = new Thread[crawlerOutPutFileCount];
		connectors = new WikiNerConnector[crawlerOutPutFileCount];
		
		for (int i = 0; i < connectors.length; i++) {
			connectors[i] = new WikiNerConnector();
			connectors[i].init(host, port, database, user, passwd);
		}
		
		for (int i = 0; i < threadList.length; i++) {
			DBWriterUnit temp = new DBWriterUnit(i, fileDest+i+".txt", connectors[i], ";#/", ";");
			threadList[i] = new Thread(temp);
			threadList[i].start();
		}
		
		for (int i = 0; i < threadList.length; i++) {
			try {
				threadList[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Gazetteer getGaz() {
		return gaz;
	}

	public void setGaz(Gazetteer gaz) {
		this.gaz = gaz;
	}

	public int getCityCount() {
		return cityCount;
	}

	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}
}
