package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import part.online.control.WeightingSystem;
import data.City;
import data.DataDump;
import data.Entity;
import data.EntityType;
import data.control.FileInput;
import data.control.StanfordNER;

public class OnlineControllerTest {
	
	private StanfordNER ner;
	private WeightingSystem ws;
	private EntityType[] entiWeig;
	private FileInput in;
	
	public static void main(String[] args) {
		OnlineControllerTest oct = new OnlineControllerTest();
		oct.compute();
	}

	private void compute() {
		Entity[] entities;
		StringBuilder temp = new StringBuilder();
		String[] file;
		ArrayList<Entity> ent = null;
		City[] result;
		
		file = in.loadCompleteFile();
		for (int i = 0; i < file.length; i++) {
			temp.append(file[i]);
			temp.append("\n");
		}
		
		try {
			ent = ner.extractEntities(temp.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		DataDump umwandler = new DataDump(ent);
		entities = umwandler.getEntityList();
		
		System.out.println("\n" + entities.length + "\n");
		long start = System.currentTimeMillis();
		result = ws.calculateCity(entities, "localhost", 3306, "wikinger2", "root", "");
		long end = System.currentTimeMillis();
		System.out.println("\n" + result.length + "\n");
		for (int i = 0; i < 20; i++) {
			System.out.println(i + ": " + result[i].getName() + " ; " + result[i].getScore());
		}
		System.out.println(end - start);
	}

	public OnlineControllerTest() {
		ner = new StanfordNER("./classifiers/english.muc.7class.distsim.crf.ser.gz");
		EntityType[] entiWeig = new EntityType[8];
		try {
			in = new FileInput("./testDatenSatz.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		entiWeig[0] = new EntityType("ORGANIZATION", 3.0);
		entiWeig[1] = new EntityType("PERSON", 2.0);
		entiWeig[2] = new EntityType("LOCATION", 4.0);
		entiWeig[3] = new EntityType("MISC", 0.0);
		entiWeig[4] = new EntityType("TIME", 0.0);
		entiWeig[5] = new EntityType("MONEY", 0.0);
		entiWeig[6] = new EntityType("PERCENT", 0.0);
		entiWeig[7] = new EntityType("DATE", 0.0);
		
		this.entiWeig = entiWeig;
		ws = new WeightingSystem(this.entiWeig);
	}

}
