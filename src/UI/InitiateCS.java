package UI;

import CellSociety.CellSocietyView;
import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;
import Simulations.Simulation;
import Utils.FireParameterParser;
import Utils.GameOfLifeParameterParser;
import Utils.ParameterParser;
import Utils.PredatorPreyParameterParser;
import Utils.SegregationParameterParser;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class InitiateCS {

	private Simulation mySimulation;
	private ParameterParser myDataFile;
	private FrontEndGrid myDisplay;
	private Color myColor;
	private double myWidth;
	private double myHeight;
	
	public InitiateCS(ParameterParser dataFile, double gridWidth, double gridHeight, Color backColor) {
		myDataFile = dataFile;
		myWidth = gridWidth;
		myHeight = gridHeight;
		myColor = backColor;
		instantiateSimulation();
		myDisplay = new FrontEndGrid(mySimulation.getMyGrid(), myWidth, myHeight, myColor);
	}
	
	public void instantiateSimulation() {
		String simType = myDataFile.getSimType();
		if (simType.equals("Fire")) {
			mySimulation = new Fire((FireParameterParser) myDataFile);
		} else if (simType.equals("GameOfLife")) {
			mySimulation = new GameOfLife((GameOfLifeParameterParser) myDataFile);
		} else if (simType.equals("PredatorPrey")) {
			mySimulation = new PredatorPrey((PredatorPreyParameterParser) myDataFile);
		} else if (simType.equals("Segregation")) {
			mySimulation = new Segregation((SegregationParameterParser) myDataFile);
		} else {
			System.exit(1);
		}
		mySimulation.initiateSimulation();
	}
	/*
	public void setGridDisplay() {
		
		myDisplay.updateGrid();
	}*/
	
	public Node getGridNode() {
		myDisplay.updateGrid();
		return myDisplay.returnDisplay();
	}
	
	public CellSocietyView getCellSociety() {
		return new CellSocietyView(mySimulation, myDisplay);
	}
}
