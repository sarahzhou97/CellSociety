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
	
	private String fileName;
	
	private static final String FIRE_FILE_NAME = "Fire.xml";
	private static final String PREDATOR_PREY_NAME = "PredatorAndPrey.xml";
	private static final String GAME_OF_LIFE_FILE_NAME = "GameOfLife.xml";
	private static final String SEGREGATION_FILE_NAME = "Segregation.xml";
	
	public FileReader(String fileName) {
		this.fileName = fileName;
	}
	
	public ParameterParser getParser() {
		if (fileName.equals(FIRE_FILE_NAME)) {
			return new FireParameterParser(fileName);
		} else if (fileName.equals(GAME_OF_LIFE_FILE_NAME)) {
			return new GameOfLifeParameterParser(fileName);
		} else if (fileName.equals(PREDATOR_PREY_NAME)) {
			return new PredatorPreyParameterParser(fileName);
		} else if (fileName.equals(SEGREGATION_FILE_NAME)) {
			return new SegregationParameterParser(fileName);
		}
		return null;
	}
	
	
}