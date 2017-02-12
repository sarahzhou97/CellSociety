package UI;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import Utils.StateSaver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIFromUser extends UserInterface{
	public static final double PREDPREY_MIN = 1;
	public static final double PREDPREY_MAX = 15;
	public static final String CATCH_PROB_STR = "probCatch";
	public static final double DEFAULT_CATCH_PROB = .2;
	public static final String STARVE_STR = "starveTime";
	public static final double DEFAULT_STARVE = 4;
	public static final String PRED_BIRTH_STR = "predatorGestationPeriod";
	public static final double DEFAULT_PRED_BIRTH = 6;
	public static final String PREY_BIRTH_STR = "preyGestationPeriod";
	public static final double DEFAULT_PREY_BIRTH = 3;
	public static final String SATIS_STR = "satisfactionRequirement";
	public static final double DEFAULT_SATIS = .1;
	public static final String METHOD_STUB = "Controls";
	
	private String mySimType;
	private HBox myControls;
	private VBox paramSliders;
	private VBox cellSliders;
	private Map<Slider, String> mySliderValues;
	private Map<String, String> myParameters;

	public GUIFromUser(Stage mainStage, String resources, Scene previousScene, String simType) {
		super(mainStage, resources, previousScene);
		mySimType = simType;
		myControls = new HBox();
		myControls.setAlignment(Pos.BASELINE_CENTER);
		paramSliders = new VBox();
		cellSliders = new VBox();
		mySliderValues = new HashMap<Slider, String>();
		myParameters = new HashMap<String, String>();
		setUserControls(simType);
		setBottomPane(myControls);
		myControls.getChildren().addAll(paramSliders, cellSliders);
		getNewSimulation();
	}

	@Override
	public void extendToolBar(Pane toolBar) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserControls(String simType) {
		try{
			Method constructControls = this.getClass().getMethod(simType+METHOD_STUB);
			constructControls.setAccessible(true);
			constructControls.invoke(this, (Object[]) null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getNewSimulation() {
		//CellsMapGenerator cmg = new CellsMapGenerator
		//Map<int[],String> cells = CellsMapGenerator
		//InitiateCS myInitializer = new InitiateCS(mySimType, myParameters, cells, GRID_SIZE_RATIO * getWidth(), GRID_SIZE_RATIO * getHeight(),
				//DEFAULT_COLOR);
		//setCellSociety(myInitializer.getCellSociety());
		//setCenterNode(myInitializer.getGridNode());
	}
	
	private void constructNewParamSlider(double defaultValue, double minVal, double maxVal, String tagString) {
		Label sliderLabel = new Label(getResources(tagString));
		Slider currSlider = new Slider(minVal, maxVal, defaultValue);
		currSlider.valueProperty().addListener(new CellListener(currSlider));
		mySliderValues.put(currSlider, tagString);
		myParameters.put(tagString, ""+defaultValue);
		paramSliders.getChildren().addAll(sliderLabel, currSlider);
	}
	
	public void updateSimulationParameters() {
		getCellSociety().updateParams(myParameters);
	}
	
	public void fireControls() {
		constructNewParamSlider(DEFAULT_CATCH_PROB, 0, 1, CATCH_PROB_STR);
	}
	
	public void gameoflifeControls() {
		//myControls.getChildren().add();
	}
	
	public void predatorpreyControls() {
		constructNewParamSlider(DEFAULT_STARVE, PREDPREY_MIN, PREDPREY_MAX, STARVE_STR);
		constructNewParamSlider(DEFAULT_PRED_BIRTH, PREDPREY_MIN, PREDPREY_MAX, PRED_BIRTH_STR);
		constructNewParamSlider(DEFAULT_PREY_BIRTH, PREDPREY_MIN, PREDPREY_MAX, PREY_BIRTH_STR);
	}
	
	public void segregationControls() {
		constructNewParamSlider(DEFAULT_SATIS, 0, 1, SATIS_STR);
	}
	
	private class CellListener implements ChangeListener<Number> {
		private Slider mySlider;
		
		public CellListener(Slider currSlider) {
			mySlider = currSlider;
		}

		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			myParameters.put(mySliderValues.get(mySlider), ""+newValue);
			updateSimulationParameters();
		}
	}
	
	@Override
	public void xmlSaver() {
		try {
			StateSaver myState = new StateSaver("empty", mySimType, new HashMap<String,String>(), new HashMap<int[],String>());
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
