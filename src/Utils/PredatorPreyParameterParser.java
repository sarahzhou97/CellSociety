package Utils;

import org.w3c.dom.Element;

public class PredatorPreyParameterParser extends ParameterParser {
	
	private double fishBreedRate;
	private double sharkBreedRate;
	private double sharkStarveRate;
	private double percentFish;
	private double percentShark;
	
	public PredatorPreyParameterParser(Element xmlElement) {
		super(xmlElement);
	}

	@Override
	protected void setParameters(Element el) {
		fishBreedRate = Double.parseDouble(el.getElementsByTagName("fishBreedRate").item(0).getTextContent());
		sharkBreedRate = Double.parseDouble(el.getElementsByTagName("sharkBreedRate").item(0).getTextContent());
		percentFish = Double.parseDouble(el.getElementsByTagName("percentFish").item(0).getTextContent());
		percentShark = Double.parseDouble(el.getElementsByTagName("percentShark").item(0).getTextContent());
		sharkStarveRate = Double.parseDouble(el.getElementsByTagName("sharkStarveRate").item(0).getTextContent());
	}
	
	public double getFishBreedRate() {
		return fishBreedRate;
	}

	public double getSharkBreedRate() {
		return sharkBreedRate;
	}

	public double getSharkStarveRate() {
		return sharkStarveRate;
	}
	
	public double getPercentFish() {
		return percentFish;
	}

	public double getPercentShark() {
		return percentShark;
	}
}
