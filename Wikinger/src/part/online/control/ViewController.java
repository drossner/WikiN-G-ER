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
	private String classifierPath;
	private FileInput fileReader;
	private String[] fileContent;
	private StanfordNER ner;
	
	public ViewController(String incPath, String classifierPath){
		this.filePath = incPath;
		this.classifierPath = classifierPath;
	}
	
	public void readIncTextFile(){
		try{
			fileReader = new FileInput(filePath);
//			System.out.println(filePath);
			fileContent = fileReader.loadCompleteFile();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void handleEntities(){
		ner = new StanfordNER(classifierPath);
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

