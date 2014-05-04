package part.offline.data;

import java.util.HashMap;

import data.control.FileInput;

public class Gazetteer {
	
	private HashMap<String, String> gaz;
	private String fileName;
	
	public Gazetteer(String fileName){
		setFileName(fileName);
	}

	public String[] loadGazetter() {
		HashMap<String, String> rc = new HashMap<String, String>();
		FileInput in = null;
		try {
			in = new FileInput(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] temp = in.loadCompleteFile();
		String[] line = null;
		String value = new String();
		
		for(int i = 0; i<temp.length; i++){
			line = temp[i].split(",");	//Gazetteer speziell
			value = line[1];			//Gazetteer speziell
			
			System.out.println("Lade " + value);
			
			if(!rc.containsKey(value)){
				rc.put(value, value);
			}
		}
		
		gaz = rc;
		
		return rc.keySet().toArray(new String[rc.size()]);
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
