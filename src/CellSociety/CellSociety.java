package CellSociety;

import Simulations.Simulation;	
import UI.MainScreen;
import UI.UserInterface;
import javafx.animation.Timeline;
//import Utils.FileReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;

public class CellSociety {
	public static final int DEFAULT_GRID_SIZE = 30;
	
	//private FileReader myFileReader = new FileReader();

	private Simulation mySimulation;
	private Timeline cellAnimation;
	
	public CellSociety(Simulation newSimulation){
		mySimulation = newSimulation;
		initializeAnimation();
	}
	
	private void initializeAnimation() {
		cellAnimation = new Timeline();
	}
	
	private void startOp() {
		
	}
	
	private void setSimulation(Simulation selectedSim) {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
