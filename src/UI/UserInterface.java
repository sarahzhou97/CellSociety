package UI;

import java.io.File;
import java.util.ResourceBundle;

import CellSociety.CellSocietyView;
import Simulations.Simulation;
import Utils.FileReader;
import Utils.ParameterParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final double GRID_SIZE_RATIO = .75;

	private CellSocietyView myCellSociety;
	private InitiateCS myInitializer;
	private Stage myStage;
	private BorderPane myScreen;
	//private ControlPanel myControlPanel;
	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private boolean runSimulation;
	private Node screenNode;
	private Scene myScene;
	private FileChooser fileBrowse;
	private ParameterParser myDataFile;
	private ResourceBundle myResources;
	private double myWidth;
	private double myHeight;

	public UserInterface(Stage mainStage, String resources) {
		fileBrowse = new FileChooser();
		// myResources = ResourceBundle.getBundle(resources);
		myStage = mainStage;
		myScreen = new BorderPane();
		//myControlPanel = new ControlPanel(resources);
		//myControlPanel.enableButtons();
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(setControlPanel());
		enableButtons();
	}

	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		// Button openFileButton = new
		// Button(myResources.getString("OpenFile"));
		Button openFileButton = new Button(("OpenFile"));
		openFileButton.setOnAction(e -> openFileBrowser());
		toolBar.getChildren().add(openFileButton);
		return toolBar;
	}
	
	private Node setControlPanel() {
		VBox controlPanel = new VBox();
		initiateControlButtons();
		controlPanel.getChildren().addAll(startButton, stepButton, stopButton, resetButton);
		return controlPanel;
	}
	
	private void initiateControlButtons() {
		startButton = initiateButton("StartButton", new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
            	runSimulation = true;
            	enableButtons();
                myCellSociety.startOp();
            }
        });
		stopButton = initiateButton("StopButton", new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
            	runSimulation = false;
            	enableButtons();
                myCellSociety.stopOp();
            }
        });
		stepButton = initiateButton("StepButton", new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myCellSociety.getNextFrame();
            }
        });
		resetButton = initiateButton("ResetButton", new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
            	runSimulation = false;
            	myCellSociety.stopOp();
            	/*enableButtons();
                myInitializer.instantiateSimulation();
                myScreen.getChildren().remove(screenNode);
                refreshDisplay();*/
            	getNewSimulation();
            }
        });
	}
	
	private Button initiateButton(String resourceString, EventHandler<ActionEvent> handler) {
		//Button controlButton = new Button(myResources.getString(resourceString));
		Button controlButton = new Button(resourceString);
		controlButton.setOnAction(handler);
		return controlButton;
	}
	/*
	 * private void setUpGridDisplay() { myDisplay = new
	 * FrontEndGrid(mySimulation.getMyGrid(), .5 * myWidth, .5 * myHeight,
	 * DEFAULT_COLOR); myDisplay.updateGrid(); }
	 */

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
			getNewSimulation();
			/*myInitializer = new InitiateCS(myDataFile, GRID_SIZE_RATIO * myWidth, GRID_SIZE_RATIO * myHeight,
					DEFAULT_COLOR);
			myCellSociety = myInitializer.getCellSociety();
			myScreen.setCenter(myInitializer.getGridNode());
			enableButtons();*/
		} else {
			return;
		}
	}
	
	private void getNewSimulation() {
		myInitializer = new InitiateCS(myDataFile, GRID_SIZE_RATIO * myWidth, GRID_SIZE_RATIO * myHeight,
				DEFAULT_COLOR);
		myCellSociety = myInitializer.getCellSociety();
		myScreen.setCenter(myInitializer.getGridNode());
		enableButtons();
	}
	
	private void refreshDisplay() {
		screenNode = myInitializer.getGridNode();
		myScreen.setCenter(screenNode);
	}

	private void enableButtons() {
		startButton.setDisable(myCellSociety==null || runSimulation);
		stepButton.setDisable(myCellSociety==null);
		stopButton.setDisable(myCellSociety==null || !runSimulation);
		resetButton.setDisable(myInitializer==null);
	}
	/*
	 * public void instantiateSimulation() { String simType =
	 * myDataFile.getSimType(); if (simType.equals("Fire")) { mySimulation = new
	 * Fire((FireParameterParser) myDataFile); } else if
	 * (simType.equals("GameOfLife")) { mySimulation = new
	 * GameOfLife((GameOfLifeParameterParser) myDataFile); } else if
	 * (simType.equals("PredatorPrey")) { mySimulation = new
	 * PredatorPrey((PredatorPreyParameterParser) myDataFile); } else if
	 * (simType.equals("Segregation")) { mySimulation = new
	 * Segregation((SegregationParameterParser) myDataFile); } else {
	 * System.exit(1); } mySimulation.initiateSimulation(); setUpGridDisplay();
	 * myScreen.setCenter(myDisplay.returnDisplay()); myCellSociety = new
	 * CellSocietyView(mySimulation, myDisplay);
	 * myControlPanel.setCellSociety(myCellSociety); }
	 */

	/*
	 * public void updateScreen() { mySimulation.update();
	 * myDisplay.updateGrid(); }
	 */
}
