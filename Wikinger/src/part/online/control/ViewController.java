package part.online.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.Entity;
import data.control.FileInput;
import data.control.StanfordNER;

public class ViewController{

	private String filePath;
	private FileInput fileReader;
	private String[] fileContent;
	private StanfordNER ner;
	
	public ViewController(String incPath){
		this.filePath = incPath;
	}
	
	public void readIncTextFile(){
		try{
			fileReader = new FileInput(filePath);
			fileContent = fileReader.loadCompleteFile();
			
			//TEST Output!
			//System.out.println(Arrays.toString(fileContent));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void handleEntities(){
		ner = new StanfordNER(filePath);
		ArrayList<Entity> allEntities;
		StringBuffer incText = new StringBuffer(Arrays.toString(fileContent));
		try{
			allEntities = ner.extractEntities(incText);
			
			for (Entity entity : allEntities){
				System.out.println(entity.toString());
			}
		} catch (IOException | ParserConfigurationException | SAXException e){
			e.printStackTrace();
		}
	}
	

}
