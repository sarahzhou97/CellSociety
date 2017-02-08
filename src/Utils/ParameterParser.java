package Utils;

import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class ParameterParser {
	
	private String simType;
	
	private Map<String,String> parameters;
	
	public ParameterParser(Element XMLElement) {
		initiateParameters(XMLElement);
	}
	
	private void initiateParameters(Element el) {
		simType = el.getAttribute("id");
		NamedNodeMap nodeMap = el.getAttributes();
		for (int i =0; i<nodeMap.getLength();i++) {
			String tagName = nodeMap.item(i).toString();
			parameters.put(tagName, el.getElementsByTagName(tagName).item(0).getTextContent());
		}
		
//		Element cell = (Element) el.getElementsByTagName("cells").item(0);
//		cellList = cell.getElementsByTagName("cell");
//		for (int idx = 0; idx<cellList.getLength();idx++) {
//			String str = cellList.item(idx).getTextContent();
//			String[] strArr = str.split(",");
//			List<Integer> coordinates = new ArrayList<Integer>();
//			coordinates.add(Integer.parseInt(strArr[0]));
//			coordinates.add(Integer.parseInt(strArr[1]));
//			initialCells.put(coordinates,strArr[2]);
//		}
	}
	
	public String getSimType() {
		return simType;
	}

	public Map<String,String> getParameters() {
		return parameters;
	}
}
