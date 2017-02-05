package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import Simulations.Simulation;
import Utils.FileReader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterface {
	public static int BUTTON_SPACING = 10;

	private Map<String, Simulation> allSimulations;
	private Simulation mySimulation;
	private ArrayList<Button> myButtons;
	private Stage myStage;
	private BorderPane myScreen;
	private Scene myScene;
	private FileChooser fileBrowse;
	private FileReader myDataFile;
	private ResourceBundle myResources;

	public UserInterface(Stage mainStage, Map<String, Simulation> possibleSimulations, String resources) {
		fileBrowse = new FileChooser();
		allSimulations = possibleSimulations;
		//myResources = ResourceBundle.getBundle(resources);
		myStage = mainStage;
		myScreen = new BorderPane();
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(setUpControlStrip());
	}
	
	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		//Button openFileButton = new Button(myResources.getString("OpenFile"));
		Button openFileButton = new Button("OpenFile");
		openFileButton.setOnAction(e -> openFileBrowser());
		toolBar.getChildren().add(openFileButton);
		Button newButton = new Button("Press Me");
		toolBar.getChildren().add(newButton);
		return toolBar;
	}
	
	private Node setUpControlStrip() {
		VBox controlStrip = new VBox();
		return controlStrip;
	}
	
	public void setUIScreen(Stage mainStage, double screenWidth, double screenHeight) {
		myScene = new Scene(myScreen, screenWidth, screenHeight);
		mainStage.setScene(myScene);
	}
	
	private void openFileBrowser() {
		File readFile = fileBrowse.showOpenDialog(myStage);
		if (readFile != null) {
			//myDataFile = new FileReader(readFile);
			//mySimulation = allSimulations.get(myDataFile.getSimulation());
			//mySimulation.initiateSimulation(myDataFile);
			//openFile(readFile);
		} else {
			return;
		}
	}
	
}
