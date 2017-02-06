package UI;

import java.io.File; 
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import CellSociety.CellSocietyView;
import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;
import Simulations.Simulation;
import Utils.FileReader;
import Utils.FireParameterParser;
import Utils.GameOfLifeParameterParser;
import Utils.ParameterParser;
import Utils.PredatorPreyParameterParser;
import Utils.SegregationParameterParser;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterface {
	public static final int BUTTON_SPACING = 10;
	public static final Color DEFAULT_COLOR = Color.WHITE;;

	private Simulation mySimulation;
	private FrontEndGrid myDisplay;
	private CellSocietyView myCellSociety;
	private Stage myStage;
	private BorderPane myScreen;
	private ControlPanel myControlPanel;
	private Scene myScene;
	private FileChooser fileBrowse;
	private ParameterParser myDataFile;
	private ResourceBundle myResources;
	private double myWidth;
	private double myHeight;
	
	public UserInterface(Stage mainStage, String resources) {
		fileBrowse = new FileChooser();
		//myResources = ResourceBundle.getBundle(resources);
		myStage = mainStage;
		myScreen = new BorderPane();
		myControlPanel = new ControlPanel(resources);
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(myControlPanel);
	}
	
	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		//Button openFileButton = new Button(myResources.getString("OpenFile"));
		Button openFileButton = new Button(("OpenFile"));
		openFileButton.setOnAction(e -> openFileBrowser());
		toolBar.getChildren().add(openFileButton);
		//Button newButton = new Button("Press Me");
		//toolBar.getChildren().add(newButton);
		return toolBar;
	}
	
	private void setUpGridDisplay() {
		myDisplay = new FrontEndGrid(mySimulation.getMyGrid(), .5*myWidth, .5*myHeight, DEFAULT_COLOR);
	}
	
	public void setUIScreen(double screenWidth, double screenHeight) {
		myWidth = screenWidth;
		myHeight = screenHeight;
		myScene = new Scene(myScreen, myWidth, myHeight);
		myStage.setScene(myScene);
	}
	
	private void openFileBrowser() {
		File readFile = fileBrowse.showOpenDialog(myStage);
		if (readFile != null) {
			FileReader myFileReader = new FileReader(readFile);
			myDataFile = myFileReader.getParser();
			String simType = myDataFile.getSimType();
			if(simType.equals("Fire")) {
				System.out.println("I did something");
				mySimulation = new Fire((FireParameterParser) myDataFile);
			} else if (simType.equals("GameOfLife")) {
				mySimulation = new GameOfLife((GameOfLifeParameterParser) myDataFile);
			} else if(simType.equals("PredatorPrey")) {
				mySimulation = new PredatorPrey((PredatorPreyParameterParser) myDataFile);
			} else if(simType.equals("Segregation")) {
				mySimulation = new Segregation((SegregationParameterParser) myDataFile);
			} else {
				System.exit(1);;
			}
			//mySimulation = allSimulations.get(myDataFile.getSimulation());
			mySimulation.initiateSimulation();
			setUpGridDisplay();
			myScreen.setCenter(myDisplay.returnDisplay());
			myCellSociety = new CellSocietyView(mySimulation, myDisplay);
			myControlPanel.setCellSociety(myCellSociety);
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
