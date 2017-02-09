package UI;

import java.io.File;
import java.util.ResourceBundle;

import CellSociety.CellSocietyView;
import Utils.ParameterParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterface {
	public static final int BUTTON_SPACING = 10;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final double GRID_SIZE_RATIO = .80;

	private CellSocietyView myCellSociety;
	private InitiateCS myInitializer;
	private Stage myStage;
	private BorderPane myScreen;
	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private Slider mySpeedSlider;
	private boolean runSimulation;
	private Scene myScene;
	private Scene backScene;
	private FileChooser fileBrowse;
	private ParameterParser myDataFile;
	private ResourceBundle myResources;
	private double myWidth;
	private double myHeight;

	public UserInterface(Stage mainStage, String resources, Scene previousScene) {
		fileBrowse = new FileChooser();
		myResources = ResourceBundle.getBundle(resources);
		backScene = previousScene;
		myStage = mainStage;
		myScreen = new BorderPane();
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(setControlPanel());
		enableButtons();
	}

	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.TOP_RIGHT);
		Button openFileButton = new Button(myResources.getString("OpenFile"));
		openFileButton.setOnAction(e -> openFileBrowser());
		Button returnToMainMenu = new Button(myResources.getString("ReturnMainMenu"));
		returnToMainMenu.setOnAction(e -> goBackToMenu());
		toolBar.getChildren().addAll(openFileButton, returnToMainMenu);
		return toolBar;
	}

	private Node setControlPanel() {
		VBox controlPanel = new VBox();
		controlPanel.setAlignment(Pos.CENTER_LEFT);
		controlPanel.setSpacing(BUTTON_SPACING);
		initiateControlButtons();
		Label sliderLabel = new Label(myResources.getString("SliderLabel"));
		controlPanel.getChildren().addAll(startButton, stepButton, stopButton, resetButton, sliderLabel, mySpeedSlider);
		return controlPanel;
	}

	private void initiateControlButtons() {
		startButton = initiateButton(myResources.getString("StartButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = true;
				enableButtons();
				myCellSociety.startOp();
			}
		});
		stopButton = initiateButton(myResources.getString("StopButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = false;
				enableButtons();
				myCellSociety.stopOp();
			}
		});
		stepButton = initiateButton(myResources.getString("StepButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myCellSociety.getNextFrame();
			}
		});
		resetButton = initiateButton(myResources.getString("ResetButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = false;
				myCellSociety.stopOp();
				getNewSimulation();
				enableButtons();
			}
		});
		mySpeedSlider = new Slider(0, 5, 1);
		mySpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myCellSociety.setRate(newValue.doubleValue());
			}

		});
	}

	private Button initiateButton(String resourceString, EventHandler<ActionEvent> handler) {
		Button controlButton = new Button(resourceString);
		controlButton.setOnAction(handler);
		controlButton.setMaxWidth(Double.MAX_VALUE);
		return controlButton;
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
			ParameterParser myParameterParser = new ParameterParser(readFile);
			myDataFile = myParameterParser;
			getNewSimulation();
		} else {
			return;
		}
	}
	
	private void goBackToMenu() {
		myStage.setScene(backScene);
	}

	private void getNewSimulation() {
		myInitializer = new InitiateCS(myDataFile, GRID_SIZE_RATIO * myWidth, GRID_SIZE_RATIO * myHeight,
				DEFAULT_COLOR);
		myCellSociety = myInitializer.getCellSociety();
		myScreen.setCenter(myInitializer.getGridNode());
		enableButtons();
	}

	private void enableButtons() {
		startButton.setDisable(myCellSociety == null || runSimulation);
		stepButton.setDisable(myCellSociety == null);
		stopButton.setDisable(myCellSociety == null || !runSimulation);
		resetButton.setDisable(myInitializer == null);
	}
}
