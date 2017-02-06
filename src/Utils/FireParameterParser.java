package Utils;

import org.w3c.dom.Element;

public class FireParameterParser extends ParameterParser{
	private String fireTitle;
	private double probCatch;
	
	public FireParameterParser(Element xmlElement) {
		super(xmlElement);
	}
	
	@Override
	protected void setParameters(Element el) {
		probCatch=Double.parseDouble(el.getElementsByTagName("probCatch").item(0).getTextContent());
	}
	
	public String getTitle() {
		return fireTitle;
	}

	public double getProbCatch() {
		return probCatch;
	}
	
}
