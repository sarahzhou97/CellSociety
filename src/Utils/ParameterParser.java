package Utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class ParameterParser {
	
	private final String TAG_NAME = "simulation";
	
	public ParameterParser(String fileName) {
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc =dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			setParameters(getXMLElement(doc));
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private Element getXMLElement(Document doc) {
		NodeList nList = doc.getElementsByTagName(TAG_NAME);
		return (Element) nList.item(0);
	}
	
	protected abstract void setParameters(Element el);
	
	public abstract String getTitle();
	
}
