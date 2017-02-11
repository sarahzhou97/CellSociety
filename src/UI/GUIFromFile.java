package UI;

import java.io.File;

import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;
import Utils.ParameterParser;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIFromFile extends UserInterface{

	public GUIFromFile(Stage mainStage, String resources, Scene previousScene) {
		super(mainStage, resources, previousScene);
		fileBrowse = new FileChooser();
		// TODO Auto-generated constructor stub
	}
	private FileChooser fileBrowse;
	/*
	public GUIFromFile() {
		fileBrowse = new FileChooser();
	}*/
	
	private void openFileBrowser() {
		File readFile = fileBrowse.showOpenDialog(getStage());
		if (readFile != null) {
			ParameterParser myParameterParser = new ParameterParser(readFile);
			String simType = myParameterParser.getSimType();
			getNewSimulation(simType);
		} else {
			return;
		}
	}

	@Override
	public void extendToolBar(Node toolBar) {
		Button openFileButton = new Button(getResources("OpenFile"));
		openFileButton.setOnAction(e -> openFileBrowser());
		
	}
	
	@Override
	public void getNewSimulation(String simType) {
		InitiateCS myInitializer = new InitiateCS(simType, GRID_SIZE_RATIO * getWidth(), GRID_SIZE_RATIO * getHeight(),
				DEFAULT_COLOR);
		setCellSociety(myInitializer.getCellSociety());
		setCenterNode(myInitializer.getGridNode());
	}
	
	
}
