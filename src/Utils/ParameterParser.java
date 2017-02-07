package Utils;

import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class ParameterParser {
	
	private String simType;
	private String myTitle;
	private int gridSize;
	
	NodeList cellList;
	
	HashMap<int[],String> initialCells;
	
	public ParameterParser(Element XMLElement) {
		initiateParameters(XMLElement);
		initialCells = new HashMap<int[],String>();
	}
	
	private void initiateParameters(Element el) {
		simType=el.getAttribute("id");
		myTitle=el.getElementsByTagName("title").item(0).getTextContent();
		gridSize = Integer.parseInt(el.getElementsByTagName("size").item(0).getTextContent());
		Element cell = (Element) el.getElementsByTagName("cells").item(0);
		cellList = cell.getElementsByTagName("cell");
		for (int idx = 0; idx<cellList.getLength();idx++) {
			String str = cellList.item(idx).getTextContent();
			String[] strArr = str.split(",");
			int[] coordinates = new int[2];
			coordinates[0] = Integer.parseInt(strArr[0]);
			coordinates[1] = Integer.parseInt(strArr[1]);
			initialCells.put(coordinates,strArr[2]);
		}
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
	
	public HashMap<int[],String> getInitialCells() {
		return initialCells;
	}
	
	protected abstract void setParameters(Element el);
	
}
