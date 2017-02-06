package Utils;

import org.w3c.dom.Element;

public abstract class ParameterParser {
	
	private String simType;
	private String myTitle;
	private int gridSize;
	
	public ParameterParser(Element XMLElement) {
		initiateParameters(XMLElement);
	}
	
	private void initiateParameters(Element el) {
		simType=el.getAttribute("id");
		myTitle=el.getElementsByTagName("title").item(0).getTextContent();
		gridSize = Integer.parseInt(el.getElementsByTagName("size").item(0).getTextContent());
		setParameters(el);
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public String getSimType() {
		return simType;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	protected abstract void setParameters(Element el);
	
}
