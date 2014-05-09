package part.offline.control;
import part.offline.data.Gazetteer;
import data.control.FileOutput;
import data.control.StanfordNER;

public class OfflineController {
	
	private String[] uniqueCityNames;
	private int cityCount;
	private StanfordNER ner;
	private Gazetteer gaz;
	
	public OfflineController(StanfordNER ner, String fileNameOldGazetteer){
		this.setGaz(new Gazetteer(fileNameOldGazetteer));
		this.ner = ner;
	}
	
	/**
	 * initialize the load of the old gazetteer
	 */
	public void init(){
		String [] uniqueCityNames = gaz.loadGazetter();
		this.uniqueCityNames = uniqueCityNames;
		setCityCount(uniqueCityNames.length);
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
		
		for (int i = 0; i < connectors.length; i++) {
			connectors[i] = new SQLConnector();
			connectors[i].init(host, port, database, user, passwd);
		}
		
		int step = cityCount/threads;
		int counter = 0;
		int rest = cityCount%threads;
		
		for (int i = 0; i < threads-1; i++) {
			//System.out.println("Starte Thread " + i);
			CrawlerUnit temp = new CrawlerUnit(uniqueCityNames, counter, counter+step-1, connectors[i], ner, i, new FileOutput(true, "CrawlerOutPut" + i +".txt"));
			threadList[i] = new Thread(temp);
			threadList[i].start();
			counter += step;	
		}
		
		CrawlerUnit temp = new CrawlerUnit(uniqueCityNames, counter, counter+step-1+rest, connectors[threads-1], ner, threads-1, new FileOutput(true, "CrawlerOutPut" + (threads-1) +".txt"));
		threadList[threads-1] = new Thread(temp);
		threadList[threads-1].start();
			
		for (int i = 0; i < threadList.length; i++) {
			try {
				threadList[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("Alle gejoint und fertig !");
		
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
