package UI;

import java.util.ArrayList;
import Simulations.Simulation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
	public static int BUTTON_SPACING = 10;

	private Stage myStage;
	private Simulation[] mySimulations;
	private ArrayList<Button> myButtons;
	private BorderPane myScreen;

	public MainScreen(Stage initStage, Simulation[] possibleSimulations) {
		myStage = initStage;
		mySimulations = possibleSimulations;
		myVBox = new VBox(BUTTON_SPACING);
	}

	public void setUpMainScreen() {
		myStage.setScene(new Scene(myVBox));
		this.getButtons();
		for (Button currentButton : myButtons) {
			myVBox.getChildren().add(currentButton);
		}
		myStage.show();
	}

	private void getButtons() {
		myButtons = new ArrayList<>();
		for (Simulation currentSimulation : mySimulations) {
			Button currentButton = new Button(currentSimulation.getTitle());
			currentButton.setOnAction(e -> currentSimulation.initiateSimulation());
			myButtons.add(currentButton);
		}
	}

}
