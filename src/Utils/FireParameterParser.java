package Utils;

import org.w3c.dom.Element;

public class FireParameterParser extends ParameterParser{
	private final String FIRE_ID = "Fire";
	private String fireTitle;
	private double probCatch;
	
	public FireParameterParser(String fileName) {
		super(fileName);
	}
	
	@Override
	protected void setParameters(Element el) {
		fireTitle=el.getElementsByTagName("title").item(0).getTextContent();
		probCatch=Double.parseDouble(el.getElementsByTagName("probCatch").item(0).getTextContent());
	}
	
	public String getTitle() {
		return fireTitle;
	}

	public double getProbCatch() {
		return probCatch;
	}
	
}
