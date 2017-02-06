package CellSociety;

import Simulations.Simulation;
import UI.FrontEndGrid;
//import UI.MainScreen;
import UI.UserInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import Utils.FileReader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;

public class CellSocietyView {
	public static final int DEFAULT_GRID_SIZE = 30;

	// private FileReader myFileReader = new FileReader();

	private Simulation mySimulation;
	private FrontEndGrid myDisplay;
	private Timeline cellAnimation;
	private double selectedSpeed;

	public CellSocietyView(Simulation newSimulation, FrontEndGrid mainDisplay) {
		mySimulation = newSimulation;
		myDisplay = mainDisplay;
		initializeAnimation();
	}

	private void initializeAnimation() {
		KeyFrame gridFrame = new KeyFrame(Duration.millis(selectedSpeed), e -> getNextFrame());
		cellAnimation = new Timeline();
		cellAnimation.setCycleCount(Timeline.INDEFINITE);
		cellAnimation.getKeyFrames().add(gridFrame);
	}

	public void startOp() {
		cellAnimation.play();
	}

	public void stopOp() {
		cellAnimation.pause();
	}
	
	public void resetSimulation() {
		cellAnimation.playFromStart();
		cellAnimation.pause();
	}

	public void getNextFrame() {
		mySimulation.update();
		myDisplay.updateGrid();
	}
}
