package Cells_Wator;

public class WatorPredator extends WatorCreature{
	private final int PREDATOR_GESTATION_PERIOD=10;
	private final int TIME_TO_STARVE=6;
	private int timeSinceAte=0;
	
	public WatorPredator(){
		setGestationPeriod(PREDATOR_GESTATION_PERIOD);
	}

}
