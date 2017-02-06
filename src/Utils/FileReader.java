package Utils;

import java.io.File; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class FileReader {
	
	private Document myDoc;
	private Element myXMLElement;
	
	public static final String TAG_NAME = "simulation";
	public static final String TAG_ID = "id";
	private static final String FIRE_FILE_NAME = "Fire";
	private static final String PREDATOR_PREY_NAME = "PredatorAndPrey";
	private static final String GAME_OF_LIFE_FILE_NAME = "GameOfLife";
	private static final String SEGREGATION_FILE_NAME = "Segregation";
	
	public FileReader(File file) {
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			myDoc = dBuilder.parse(file);
			myDoc.getDocumentElement().normalize();
			NodeList nList = myDoc.getElementsByTagName(TAG_NAME);
			myXMLElement = (Element) nList.item(0);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public ParameterParser getParser() {
		String simName = myXMLElement.getAttribute(TAG_ID);
		if (simName.equals(FIRE_FILE_NAME)) {
			return new FireParameterParser(myXMLElement);
		} else if (simName.equals(GAME_OF_LIFE_FILE_NAME)) {
			return new GameOfLifeParameterParser(myXMLElement);
		} else if (simName.equals(PREDATOR_PREY_NAME)) {
			return new PredatorPreyParameterParser(myXMLElement);
		} else if (simName.equals(SEGREGATION_FILE_NAME)) {
			return new SegregationParameterParser(myXMLElement);
		}
		return null;
	}
	
	
}