package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import Simulations.Simulation;
import Utils.FileReader;
import Utils.ParameterParser;
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

	private Simulation mySimulation;
	private ArrayList<Button> myButtons;
	private Stage myStage;
	private BorderPane myScreen;
	private Scene myScene;
	private FileChooser fileBrowse;
	private ParameterParser myDataFile;
	private ResourceBundle myResources;
	private boolean runSimulation;

	public UserInterface(Stage mainStage, String resources) {
		fileBrowse = new FileChooser();
		myResources = ResourceBundle.getBundle(resources);
		myStage = mainStage;
		myScreen = new BorderPane();
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(new ControlPanel(resources));
	}
	
	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		Button openFileButton = new Button(myResources.getString("OpenFile"));
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
	
	public void setUIScreen(double screenWidth, double screenHeight) {
		myScene = new Scene(myScreen, screenWidth, screenHeight);
		myStage.setScene(myScene);
	}
	
	private void openFileBrowser() {
		File readFile = fileBrowse.showOpenDialog(myStage);
		if (readFile != null) {
			//FileReader myFileReader = new FileReader(readFile);
			//myDataFile = myFileReader.getParser();
			//mySimulation = allSimulations.get(myDataFile.getSimulation());
			//mySimulation.initiateSimulation(myDataFile);
			//playSimulation();
		} else {
			return;
		}
	}
	/*
	private void playSimulation() {
		while(!mySimulation.sameGrid()) {
			if(!runSimulation) {
				updateSimFrame();
			}
		}
	}
	
	private void updateSimFrame() {
		mySimulation.update();
	}
	*/
}
