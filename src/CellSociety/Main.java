package CellSociety;

import UI.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String RESOURCE_TAG = "resources/";

	private UserInterface myUI;
	private String resourceType = "English";

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Cell Society");
		myUI = new UserInterface(primaryStage, RESOURCE_TAG + resourceType);
		myUI.setUIScreen(800, 800);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
