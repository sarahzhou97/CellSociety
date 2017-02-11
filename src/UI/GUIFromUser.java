package UI;

import java.lang.reflect.Method;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIFromUser extends UserInterface{

	public GUIFromUser(Stage mainStage, String resources, Scene previousScene, String simType) {
		super(mainStage, resources, previousScene);
		try{
			Method constructControls = this.getClass().getMethod(simType);
			constructControls.setAccessible(true);
			constructControls.invoke(this, (Object[]) null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void extendToolBar(Pane toolBar) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserControls() {
		
	}

	@Override
	public void getNewSimulation() {
		// TODO Auto-generated method stub
		
	}
	
	private void fireControls() {
		
	}
	
	private void gofControls() {
		
	}
	
	private void watorControls() {
		
	}
	
	private void segregationControls() {
		
	}

}
