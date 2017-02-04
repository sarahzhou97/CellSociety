package Cells;

public abstract class WatorCreature extends Cell{

	@Override
	public void updateState(String newState) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveTo(int x, int y){
		setRow(y);
		setCol(x);
	}

}
