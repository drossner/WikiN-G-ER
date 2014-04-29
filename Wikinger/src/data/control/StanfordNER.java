package data.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.xpath.XPathExpressionException;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;

public class StanfordNER
{

	//	_________________________________Variables______________________________________
	private AbstractSequenceClassifier<?> classifier;
	private String serializedClassifier;
	//private EntityFilter filter;

//	_________________________________Constructors___________________________________
	
	/**
	 * Constructor which handles an commited Classifier Filename 
	 * @param clfFilename
	 */
	public StanfordNER(String clfFilename)
	{
		serializedClassifier = clfFilename;
		classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
	}

	
//	_______________________________Methods_________________________________________
	/**
	 * OFFLINE-PART: extracts the Entities from the Output of the Crawler-Component
	 * @param crawlerOutput
	 */
	/**
	public City extractEntities(CrawlerOutput crawlerOutput)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(crawlerOutput.getContent());
		ArrayList<Entity> entities = extractEntities(buffer);
		
		City cityWithEntities = new City(entities, crawlerOutput.getName(), 
				crawlerOutput.getLongitude(), crawlerOutput.getLatitude());
		
		return cityWithEntities;
	}*/
	
	/**
	 * ONLINE-PART: extracts all Entities using the Stanford NER and sends them to CWS Component
	 * @param textDoc
	 * @return ArrayList<Entity>
	 * @throws JAXBException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws XPathExpressionException 
	 */
	public String extractEntities(StringBuffer textDoc) throws JAXBException{

		ArrayList<String> entities;
		String text  = textDoc.toString();
		String resultInXml = classifier.classifyToString(text, "xml", false);
		StringBuffer buffer = new StringBuffer("<root>");
		buffer.append(resultInXml);
		buffer.append("</root>");
		System.out.println(buffer);
		
		
		/*
		entities = new ArrayList<String>();
		String category, name, oldName, oldCategory;
		int categoryEnd;
		
	//	System.out.println(resultInXml); //kann man mit ausgeben, TESTZWECK!
		
		String[] temp = resultInXml.split("</wi>");
		
		String[] temp2 = temp[0].split("=");
		categoryEnd = temp2[2].indexOf(">");

		oldCategory = temp2[2].substring(1, categoryEnd - 1);
		oldName = temp2[2].substring(categoryEnd + 1, temp2[2].length());
		
		for (int i = 1; i < temp.length - 1; i++){
			
			temp2 = temp[i].split("=");
			categoryEnd = temp2[2].indexOf(">");
			category = temp2[2].substring(1, categoryEnd - 1);
			name = temp2[2].substring(categoryEnd + 1, temp2[2].length());
			
			if ( category.equals(oldCategory) && !category.equals("O")){
				oldName += " " + name;
			}
			else if(!category.equals("O")){
				oldName += " " + name;
			}
			else if(!oldCategory.equals("O")){
				Entity e = new Entity(oldName.trim(), oldCategory.trim());
				entities.add(e);
				oldName = "";
			}
			oldCategory = category;
			
		}*/
		return resultInXml;
	}
	
	/**
	 * returns the actual serialized Classifier
	 * @return serializedClassifier
	 */
	public String getSerializedClassifier()
	{
		return serializedClassifier;
	}

	/**
	 * sets the current serializedClassifier
	 * @param serializedClassifier
	 */
	public void setSerializedClassifier(String serializedClassifier)
	{
		this.serializedClassifier = serializedClassifier;
	}
	
	/**
	 * returns the current CRFClassifier
	 * @return CRFClassifier<?>
	 */
	public CRFClassifier<?> getClassifier(){
		return (CRFClassifier<?>) classifier;
	}
}
