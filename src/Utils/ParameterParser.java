package Utils;

import org.w3c.dom.Element;

public abstract class ParameterParser {
	
	private String simType;
	private String myTitle;
	
	public ParameterParser(Element XMLElement) {
		initiateParameters(XMLElement);
	}
	
	private void initiateParameters(Element el) {
		simType=el.getAttribute("id");
		myTitle=el.getElementsByTagName("title").item(0).getTextContent();
		setParameters(el);
	}
	
	public String getSimType() {
		return simType;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	protected abstract void setParameters(Element el);
	
}
