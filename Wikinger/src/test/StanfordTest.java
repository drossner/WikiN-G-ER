package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.jdom.JDOMException;
import org.xml.sax.SAXException;

import data.Entity;
import data.control.FileInput;
import data.control.StanfordNER;

public class StanfordTest
{

	public static void main(String[] args)
	{
		String[] incText = null;
		try{
			FileInput input = new FileInput("C:/Users/Mario/Dropbox/Projektordner/Semester 6/Wikiner/Crawlertexte.txt");
			incText = input.loadCompleteFile();
		} catch (Exception e1){
			e1.printStackTrace();
		}
		
		StringBuffer textDoc = new StringBuffer(Arrays.toString(incText));
		System.out.println(textDoc);
		
		
		StanfordNER ner = new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz");
		ArrayList<Entity> temp = null;
			
		try
		{
			temp = ner.extractEntities(textDoc);
	
			for (Entity entity : temp){
				System.out.println(entity.toString());
			}
		} catch (IOException | ParserConfigurationException | SAXException e)
		{
			e.printStackTrace();
		}
	}

}
