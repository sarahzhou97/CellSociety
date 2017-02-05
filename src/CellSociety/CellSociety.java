package CellSociety;

import Simulations.Simulation;	
import UI.MainScreen;
import UI.UserInterface;
//import Utils.FileReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;

public class CellSociety extends Application {
	public static final int DEFAULT_GRID_SIZE = 30;
	
	//private FileReader myFileReader = new FileReader();

	private UserInterface myMainScreen;
	/*private Simulation[] possibleSimulations = { 
			new Fire(DEFAULT_GRID_SIZE,myFileReader.getProbCatch(),myFileReader.getFireTitle()), 
			new GameOfLife(DEFAULT_GRID_SIZE,myFileReader.getGameOfLifeTitle()),
			new PredatorPrey(DEFAULT_GRID_SIZE,myFileReader.getPredatorPreyTitle()), 
			new Segregation(DEFAULT_GRID_SIZE,myFileReader.getSegregationTitle()) };*/

	public void start(Stage myStage) throws Exception {
		myStage.setTitle("Cell Society");
		myMainScreen = new UserInterface(myStage, new HashMap<String, Simulation>(), "Hello");
		myMainScreen.setUIScreen(myStage, 1000, 500);
		myStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
