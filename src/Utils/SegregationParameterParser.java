package Utils;

import org.w3c.dom.Element;

public class SegregationParameterParser extends ParameterParser {
	private double percentSatisfied;
	private double redBlueRatio;
	private double percentEmpty;
	
	public SegregationParameterParser(Element xmlElement) {
		super(xmlElement);
	}
	
	@Override
	protected void setParameters(Element el) {
		redBlueRatio = Double.parseDouble(el.getElementsByTagName("redBlueRatio").item(0).getTextContent());
		percentSatisfied = Double.parseDouble(el.getElementsByTagName("percentSatisfied").item(0).getTextContent());
		percentEmpty = Double.parseDouble(el.getElementsByTagName("percentEmpty").item(0).getTextContent());
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
