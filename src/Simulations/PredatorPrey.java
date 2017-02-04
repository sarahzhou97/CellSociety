package Simulations;

import java.util.List;

import Cells.Cell;
import Cells_Wator.WatorCreature;
import Cells_Wator.WatorEmpty;
import UI.Grid;

public class PredatorPrey extends Simulation {
	public PredatorPrey(int size, String title) {
		super(size,title);
	}
	
	public void moveToAndReplace(int x, int y,Cell creature){//Replaces target cell and leaves blank cell behind
		getMyGrid().setCell(creature.getRow(), creature.getCol(), new WatorEmpty());
		creature.setRow(x);
		creature.setCol(y);
		getMyGrid().setCell(x,y,creature);
	}

	public void switchCell(Cell cell1, Cell cell2){
		int cell2X=cell2.getRow();
		int cell2Y=cell2.getCol();
		moveToAndReplace(cell1.getRow(),cell1.getCol(),cell2);
		moveToAndReplace(cell2X,cell2Y,cell1);	
	}
	
	public void handleReproduction(WatorCreature creature){
		if(creature.getGestationPeriod()==creature.getTimeSinceBirth()){
			birthIfAble(creature);
			creature.resetTimeSinceBirth();
		}
		else{
			creature.incrementTimeSinceBirth();
		}
	}
	
	public void birthIfAble(WatorCreature creature){
		/*
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		for(Cell cell:cellList){
			
		}*/
		
	}
	
	public void moveIfAble(){
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateSimulation() {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}