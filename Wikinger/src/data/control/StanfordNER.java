
package data.control;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import data.Entity;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.w3c.dom.*;

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
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException
	 */
	public ArrayList<Entity> extractEntities(StringBuffer textDoc) throws IOException, ParserConfigurationException, SAXException{

		ArrayList<Entity> entities = new ArrayList<Entity>();
		String text  = textDoc.toString();
		String resultInXml = classifier.classifyToString(text, "xml", false);
		StringBuffer buffer = new StringBuffer("<root>");
		buffer.append(resultInXml);
		buffer.append("</root>");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(buffer.toString()));
		
		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("wi");
		
		for (int i = 0; i < nodes.getLength(); i++){
			Element element = (Element) nodes.item(i);
			
			if(!element.getAttribute("entity").equals("O")){
				entities.add(new Entity(element.getTextContent(), element.getAttribute("entity")));
			}
			
		}
		
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
		return entities;
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
