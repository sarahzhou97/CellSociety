package Utils;

import org.w3c.dom.Element;

public class SegregationParameterParser extends ParameterParser {
	private final String SEGREGATION_ID = "Segregation";
	private String segregationTitle;
	private double percentSatisfied;
	private double redBlueRatio;
	private double percentEmpty;
	
	public SegregationParameterParser(String fileName) {
		super(fileName);
	}
	
	@Override
	protected void setParameters(Element el) {
		segregationTitle=el.getElementsByTagName("title").item(0).getTextContent();
		redBlueRatio = Double.parseDouble(el.getElementsByTagName("redBlueRatio").item(0).getTextContent());
		percentSatisfied = Double.parseDouble(el.getElementsByTagName("percentSatisfied").item(0).getTextContent());
		percentEmpty = Double.parseDouble(el.getElementsByTagName("percentEmpty").item(0).getTextContent());
	}
	@Override
	public String getTitle() {
		return segregationTitle;
	}
	
	public double getRedBlueRatio() {
		return redBlueRatio;
	}

	public double getPercentEmpty() {
		return percentEmpty;
	}

	public double getPercentSatisfied() {
		return percentSatisfied;
	}

	public void setPercentSatisfied(double percentSatisfied) {
		this.percentSatisfied = percentSatisfied;
	}
}
