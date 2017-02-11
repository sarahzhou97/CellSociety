package UI;

import java.util.Map;

import CellSociety.CellSocietyView;
import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;
import Simulations.Simulation;
import UI_FrontEndGrid.BaseFrontEndGrid;
import Utils.ParameterParser;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class InitiateCS {

	private Simulation mySimulation;
	private ParameterParser myDataFile;
	private BaseFrontEndGrid myDisplay;
	private Color myColor;
	private double myWidth;
	private double myHeight;
	Map<String,String> myParameters;
	Map<int[], String> myCells;
	
	public InitiateCS(String sim, double gridWidth, double gridHeight, Color backColor) {
		myWidth = gridWidth;
		myHeight = gridHeight;
		myColor = backColor;	
		instantiateSimulation(sim);
		myDisplay = new BaseFrontEndGrid(mySimulation.getMyGrid(), myWidth, myHeight, myColor);
	}
	
	public void instantiateSimulation(String simType) {
		/*String simType = myDataFile.getSimType();
		myParameters = myDataFile.getParameters();
		if (simType.equals("Fire")) {
			mySimulation = new Fire(myParameters);
		} else if (simType.equals("GameOfLife")) {
			mySimulation = new GameOfLife(myParameters);
		} else if (simType.equals("PredatorPrey")) {
			mySimulation = new PredatorPrey(myParameters);
		} else if (simType.equals("Segregation")) {
			mySimulation = new Segregation(myParameters);
		} else {
			System.exit(1);
		}*/
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
	
	public String getSimTitle() {
		return myParameters.get("title");
	}
}