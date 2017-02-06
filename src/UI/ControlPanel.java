package UI;

import java.util.ResourceBundle;

import Simulations.Simulation;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox{

	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private ResourceBundle myResources;
	private Simulation mySimulation;
	private boolean runSimulation;
	
	public ControlPanel(String resources) {
		myResources = ResourceBundle.getBundle(resources);
		runSimulation = false;
		runSimulationLoop();
	}
	
	private void initiateStartButton() {
		startButton = new Button(myResources.getString("StartButton"));
		startButton.setOnAction(e -> startOp());
	}
	
	private void startOp() {
		runSimulation = true;
		runSimulationLoop();
		enableButtons();
	}
	
	private void stopOp() {
		runSimulation = false;
		enableButtons();
	}
	
	private void enableButtons() {
		startButton.setDisable(mySimulation==null && runSimulation);
		stepButton.setDisable(mySimulation==null);
		stopButton.setDisable(mySimulation==null && !runSimulation);
		resetButton.setDisable(mySimulation==null);
	}
	
	public void setSimulation(Simulation newSimulation) {
		mySimulation = newSimulation;
		enableButtons();
	}
	
	private void runSimulationLoop() {
		if(mySimulation!=null) {
			while(runSimulation) {
				mySimulation.update();
			}
		}
	}
}
