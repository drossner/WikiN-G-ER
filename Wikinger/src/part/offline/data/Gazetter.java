package part.offline.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Gazetter {
	
	private HashMap<String, String> gaz;
	private String fileName;
	
	public Gazetter(String fileName){
		setFileName(fileName);
	}
	
	public String[] loadGaz(){
		BufferedReader reader = null;
		String line = new String("");
		String value = new String("");
		String temp[] = new String[15];
		gaz = new HashMap<String, String>();
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		}
		
		try {
			line = reader.readLine();
			
			while(line != null){
				temp = line.split(",");
				value = temp[1];
				if(gaz.containsKey(value)){
					System.out.println("Stadt " + value + " ist schon vorhanden!");
				}else{
					System.out.println("Stadt " + value + " wird dem gaz hinzugefügt");
					gaz.put(value, value);
				}
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen");
			e.printStackTrace();
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("File-Close geht nicht !");
			e.printStackTrace();
		}
		return (String[]) gaz.keySet().toArray();
	}

	public HashMap<String, String> getGaz() {
		return gaz;
	}

	public void setGaz(HashMap<String, String> gaz) {
		this.gaz = gaz;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
