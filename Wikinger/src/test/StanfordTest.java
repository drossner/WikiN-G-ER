package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import data.Entity;
import data.control.StanfordNER;

public class StanfordTest
{

	public static void main(String[] args)
	{
		StringBuffer textDoc = new StringBuffer("Paris (English /ˈpærɪs/, Listeni/ˈpɛərɪs/; French: [paʁi] ( listen)) is the capital and most populous city of France. It is situated on the Seine River, in the north of the country, at the heart of the Île-de-France region. Within its administrative limits (the 20 arrondissements), the city had 2,234,105 inhabitants in 2009 while its metropolitan area is one of the largest population centres in Europe with more than 12 million inhabitants.An important settlement for more than two millennia, by the late 12th century Paris had become a walled cathedral city that was one of Europe's foremost centres of learning and the arts and the largest city in the Western world until the turn of the 18th century. Paris was the focal point for many important political events throughout its history, including the French Revolution. Today it is one of the worlds leading business and cultural centres, and its influence in politics, education, entertainment, media, science, fashion and the arts all contribute to its status as one of the worlds major cities. The city has one of the largest GDPs in the world, €607 billion (US$845 billion) as of 2011, and as a result of its high concentration of national and international political, cultural and scientific institutions is one of the worlds leading tourist destinations. The Paris Region hosts the world headquarters of 30 of the Fortune Global 500 companies[6] in several business districts, notably La Défense, the largest dedicated business district in Europe.[7]");
		
		
		StanfordNER ner = new StanfordNER("./classifiers/english.all.3class.distsim.crf.ser.gz");
		ArrayList<Entity> temp = null;
			
		try
		{
			temp = ner.extractEntities(textDoc);
			for (Entity string : temp){
				System.out.println(string.getName());
			}
		} catch (IOException | ParserConfigurationException | SAXException e)
		{
			e.printStackTrace();
		}
	}

}
