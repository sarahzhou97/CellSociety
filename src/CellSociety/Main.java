package CellSociety;

import java.util.HashMap;

import Simulations.Simulation;
import UI.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	private UserInterface myUI;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Cell Society");
		myUI = new UserInterface(primaryStage, "English.properties");
		myUI.setUIScreen(800, 800);
		primaryStage.show();
	}
	
	public static void main (String[] args) {
        launch(args);
    }

}
