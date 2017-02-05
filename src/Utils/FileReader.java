package Utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Simulations.Simulation;

public class FileReader {
	
	private final String FILE = "DATA.xml";
	private final String TAG_NAME = "simulation";
	NodeList nList;
	
	private final String FIRE_ID = "Fire";
	private String fireTitle;
	private double probCatch;
	
	private final String SEGREGATION_ID = "Segregation";
	private String segregationTitle;
	private double percentSimilar;
	private double redBlueRatio;
	private double percentEmpty;
	
	private final String GAME_OF_LIFE_ID = "GameOfLife";
	private String gameOfLifeTitle;
	
	private final String PREDATOR_PREY_ID = "PredatorPrey";
	private String predatorPreyTitle;
	private double fishBreedRate;
	private double sharkBreedRate;
	private double sharkStarveRate;
	private double percentFish;
	private double percentShark;
	
	public FileReader(File file) {
		try{
			//File file = new File(FILE);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc =dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			nList = doc.getElementsByTagName(TAG_NAME);
			extractSimulationParameters();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private void extractSimulationParameters() {
		for (int idx = 0; idx < nList.getLength(); idx++) {
			Node nNode = nList.item(idx);
			Element el = (Element) nNode;
			if (el.getAttribute("id").equals(FIRE_ID)) {
				setFireParameters(el);
			} else if (el.getAttribute("id").equals(SEGREGATION_ID)) {
				setSegregationParameters(el);
			} else if (el.getAttribute("id").equals(GAME_OF_LIFE_ID)) {
				setGameOfLifeParameters(el);
			} else if (el.getAttribute("id").equals(PREDATOR_PREY_ID)) {
				setPredatorPreyParameters(el);
			}
		}
	}
	
	private void setFireParameters(Element el) {
		this.fireTitle=el.getElementsByTagName("title").item(0).getTextContent();
		this.probCatch=Double.parseDouble(el.getElementsByTagName("probCatch").item(0).getTextContent());
	}
	
	private void setSegregationParameters(Element el) {
		segregationTitle=el.getElementsByTagName("title").item(0).getTextContent();
		redBlueRatio = Double.parseDouble(el.getElementsByTagName("redBlueRatio").item(0).getTextContent());
		percentSimilar = Double.parseDouble(el.getElementsByTagName("percentSimilar").item(0).getTextContent());
		percentEmpty = Double.parseDouble(el.getElementsByTagName("percentEmpty").item(0).getTextContent());
	}
	
	private void setGameOfLifeParameters(Element el) {
		gameOfLifeTitle = el.getElementsByTagName("title").item(0).getTextContent();
	}
	
	private void setPredatorPreyParameters(Element el) {
		predatorPreyTitle = el.getElementsByTagName("title").item(0).getTextContent();
		fishBreedRate = Double.parseDouble(el.getElementsByTagName("fishBreedRate").item(0).getTextContent());
		sharkBreedRate = Double.parseDouble(el.getElementsByTagName("sharkBreedRate").item(0).getTextContent());
		percentFish = Double.parseDouble(el.getElementsByTagName("percentFish").item(0).getTextContent());
		percentShark = Double.parseDouble(el.getElementsByTagName("percentShark").item(0).getTextContent());
		sharkStarveRate = Double.parseDouble(el.getElementsByTagName("sharkStarveRate").item(0).getTextContent());
	}
	
	public String getFireTitle() {
		return fireTitle;
	}

	public double getProbCatch() {
		return probCatch;
	}

	public String getSegregationTitle() {
		return segregationTitle;
	}

	public double getRedBlueRatio() {
		return redBlueRatio;
	}

	public double getPercentEmpty() {
		return percentEmpty;
	}

	public String getGameOfLifeTitle() {
		return gameOfLifeTitle;
	}

	public String getPredatorPreyTitle() {
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
