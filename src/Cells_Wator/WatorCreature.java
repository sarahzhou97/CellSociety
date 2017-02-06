package Cells_Wator;

import Cells.Cell;

public abstract class WatorCreature extends Cell{
	private int timeSinceBirth=0;
	private int gestationPeriod;

	@Override
	public void updateState(String newState) {
		// TODO Auto-generated method stub
		
	}
	
	public void resetTimeSinceBirth(){
		timeSinceBirth=0;
	}
	
	public void setGestationPeriod(int period){
		gestationPeriod=period;
	}
	
	public int getGestationPeriod(){
		return gestationPeriod;
	}
	
	public int getTimeSinceBirth(){
		return timeSinceBirth;
	}
	
	public void incrementTimeSinceBirth(){
		timeSinceBirth++;
	}
	
	public abstract WatorCreature makeChild();
}
