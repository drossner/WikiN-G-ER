package part.offline.data;

import java.util.HashMap;

public class Gazetter {
	
	private HashMap<String, String> gaz;
	private String fileName;
	
	public Gazetter(String fileName){
		setFileName(fileName);
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
