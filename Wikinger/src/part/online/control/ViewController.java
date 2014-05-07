package part.online.control;

import data.control.FileInput;

public class ViewController{

	private String filePath;
	private FileInput fileReader;
	private String[] fileContent;
	
	public ViewController(String incPath){
		this.filePath = incPath;
	}
	
	public void readIncTextFile(){
		try{
			fileReader = new FileInput(filePath);
			fileContent = fileReader.loadCompleteFile();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//TODO handle read content of the txt File and deliver it to StanfordNER
	}
	

}
