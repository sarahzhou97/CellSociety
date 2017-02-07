package Utils;

import java.util.ArrayList;
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
		initialCells = new HashMap<int[],String>();
		initiateParameters(XMLElement);
	}
	
	private void initiateParameters(Element el) {
		simType=el.getAttribute("id");
		System.out.println("hi");
		myTitle=el.getElementsByTagName("title").item(0).getTextContent();
		System.out.println(myTitle);
		gridSize = Integer.parseInt(el.getElementsByTagName("size").item(0).getTextContent());
		//System.out.println(gridSize);
		System.out.println("hi3");
		Element cell = (Element) el.getElementsByTagName("cells").item(0);
		//System.out.println(el.getElementsByTagName("cells").item(0).getNodeName());
		//System.out.println("hi4");
		cellList = cell.getElementsByTagName("cell");
		for (int idx = 0; idx<cellList.getLength();idx++) {
			String str = cellList.item(idx).getTextContent();
			//System.out.println(str);
			String[] strArr = str.split(",");
			int[] coordinates = new int[2];
			coordinates[0] = Integer.parseInt(strArr[0]);
			coordinates[1] = Integer.parseInt(strArr[1]);
			System.out.println(coordinates[0]+" "+coordinates[1]);
			System.out.println(strArr[2]);
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
