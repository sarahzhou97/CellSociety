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
		myUI = new UserInterface(primaryStage, "Hello");
		myUI.setUIScreen(1000, 500);
		primaryStage.show();
		
	}
	
	public static void main (String[] args) {
        CellSociety application = new CellSociety();
        application.launch(args);
    }

}
