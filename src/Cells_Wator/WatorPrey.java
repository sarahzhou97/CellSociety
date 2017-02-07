package Cells_Wator;

import javafx.scene.paint.Color;

public class WatorPrey extends WatorCreature{
	private final int PREY_GESTATION_PERIOD=5;
	private final Color PREY_COLOR=Color.YELLOW;
	
	public WatorPrey(){
		super();
		setColor(PREY_COLOR);
		setGestationPeriod(PREY_GESTATION_PERIOD);
	}
	
	@Override
	public WatorPrey makeChild(){
		return new WatorPrey();
	}

}
