package part.online.control;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.City;
import data.DataDump;
import data.Entity;
import data.EntityType;
import data.control.FileInput;
import data.control.StanfordNER;
import data.database.connection.WikiNerGraphConnector;

public class ViewController {

	private StanfordNER ner;
	private WikiNerGraphConnector connector;

	public ViewController() {
		ner = new StanfordNER(
				"./classifiers/english.muc.7class.distsim.crf.ser.gz");
		connector = WikiNerGraphConnector.getInstance("./database");
	}

	public City[] calculate(double[] weightings, String inFilePath) {
		// Gewichtungen werden gesetzt
		EntityType[] entiWeig = new EntityType[8];

		entiWeig[0] = new EntityType("ORGANIZATION", weightings[0]);
		entiWeig[1] = new EntityType("PERSON", 0.0);
		entiWeig[2] = new EntityType("LOCATION", 2.0);
		entiWeig[3] = new EntityType("MISC", 0.0);
		entiWeig[4] = new EntityType("TIME", 0.0);
		entiWeig[5] = new EntityType("MONEY", 0.0);
		entiWeig[6] = new EntityType("PERCENT", 0.0);
		entiWeig[7] = new EntityType("DATE", 0.0);
		// WeightingSystem erzeugen
		WeightingSystem ws = new WeightingSystem(entiWeig);

		// inFile aufbauen
		String text = readIncTextFile(inFilePath);
		
		//Entities extrahieren
		Entity[] extractedEntities;
		ArrayList<Entity> tempEntList = new ArrayList<Entity>();
		
		try{
			tempEntList = ner.extractEntities(text);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		DataDump umwandler = new DataDump(tempEntList); // Mehrfache Entities werden zusammengefasst
		extractedEntities = umwandler.getEntityList();
		
		//Result anlegen und zurückliefern
		City[] result = ws.calculateCity(extractedEntities, "./database");
		
		return result;
		
	}

	/**
	 * reads the inputed .txt File
	 */
	public String readIncTextFile(String inFilePath) {
		String[] fileContent = null;
		StringBuffer text = new StringBuffer();
		try {
			FileInput fileReader = new FileInput(inFilePath);
			fileContent = fileReader.loadCompleteFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < fileContent.length && fileContent != null; i++) {
			text.append(fileContent[i]);
			text.append(System.lineSeparator());
		}
		
		return text.toString();
	}
	
	/**
	 * Beendet die Datenbank-Verbindung
	 */
	public void endConnection(){
		connector.shutdown();
	}
}