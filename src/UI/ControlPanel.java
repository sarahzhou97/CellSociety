package UI;

import java.util.ResourceBundle;

import CellSociety.CellSocietyView;
import Simulations.Simulation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox{

	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private ResourceBundle myResources;
	private Simulation mySimulation;
	private CellSocietyView myCellSociety;
	private boolean runSimulation;
	
	public ControlPanel(String resources) {
		//myResources = ResourceBundle.getBundle(resources);
		runSimulation = false;
		initiateControlButtons();
		this.getChildren().addAll(startButton, stepButton, stopButton, resetButton);
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
            	enableButtons();
                myCellSociety.resetSimulation();
            }
        });
	}
	
	private Button initiateButton(String resourceString, EventHandler<ActionEvent> handler) {
		//Button controlButton = new Button(myResources.getString(resourceString));
		Button controlButton = new Button(resourceString);
		controlButton.setOnAction(handler);
		return controlButton;
	}
	
	public void setCellSociety(CellSocietyView newCS) {
		myCellSociety = newCS;
	}
	
	/*
	private void startOp() {
		runSimulation = true;
		runSimulationLoop();
		enableButtons();
	}
	
	private void stopOp() {
		runSimulation = false;
		enableButtons();
	}*/
	
	private void enableButtons() {
		startButton.setDisable(myCellSociety==null && runSimulation);
		stepButton.setDisable(myCellSociety==null);
		stopButton.setDisable(myCellSociety==null && !runSimulation);
		resetButton.setDisable(myCellSociety==null);
	}
}
