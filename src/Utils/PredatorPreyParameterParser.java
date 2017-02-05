package Utils;

import org.w3c.dom.Element;

public class PredatorPreyParameterParser extends ParameterParser {
	
	private String predatorPreyTitle;
	private double fishBreedRate;
	private double sharkBreedRate;
	private double sharkStarveRate;
	private double percentFish;
	private double percentShark;
	
	public PredatorPreyParameterParser(String fileName) {
		super(fileName);
	}

	@Override
	protected void setParameters(Element el) {
		predatorPreyTitle = el.getElementsByTagName("title").item(0).getTextContent();
		fishBreedRate = Double.parseDouble(el.getElementsByTagName("fishBreedRate").item(0).getTextContent());
		sharkBreedRate = Double.parseDouble(el.getElementsByTagName("sharkBreedRate").item(0).getTextContent());
		percentFish = Double.parseDouble(el.getElementsByTagName("percentFish").item(0).getTextContent());
		percentShark = Double.parseDouble(el.getElementsByTagName("percentShark").item(0).getTextContent());
		sharkStarveRate = Double.parseDouble(el.getElementsByTagName("sharkStarveRate").item(0).getTextContent());
	}

	@Override
	public String getTitle() {
		return predatorPreyTitle;
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
