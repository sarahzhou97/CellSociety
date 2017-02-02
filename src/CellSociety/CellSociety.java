package CellSociety;

import Simulations.Simulation;
import UI.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;

public class CellSociety extends Application {
	public static final int DEFAULT_GRID_SIZE = 30;

	private MainScreen myMainScreen;
	private Simulation[] possibleSimulations = { new Fire(DEFAULT_GRID_SIZE), new GameOfLife(DEFAULT_GRID_SIZE),
			new PredatorPrey(DEFAULT_GRID_SIZE), new Segregation(DEFAULT_GRID_SIZE) };

	public void start(Stage myStage) throws Exception {
		myStage.setTitle("Cell Society");
		myMainScreen = new MainScreen(myStage, possibleSimulations);
		myMainScreen.setUpMainScreen();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
