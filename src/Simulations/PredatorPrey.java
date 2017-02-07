package Simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BackEndGrid.BackEndGrid;
import Cells.Cell;
import Cells.SegregationCell;
import Cells_Wator.WatorCreature;
import Cells_Wator.WatorEmpty;
import Cells_Wator.WatorPredator;
import Cells_Wator.WatorPrey;
import Utils.PredatorPreyParameterParser;

public class PredatorPrey extends Simulation {
	public PredatorPrey(PredatorPreyParameterParser parameters) {
		super(parameters);
	}
	
	public void moveToAndReplace(int x, int y,Cell creature){//Replaces target cell and leaves blank cell behind
		getMyGrid().setCell(creature.getRow(), creature.getCol(), new WatorEmpty());
		creature.setRow(x);
		creature.setCol(y);
		getMyGrid().setCell(x,y,creature);
	}

	public void switchCell(Cell cell1, Cell cell2){
		int cell2Row=cell2.getRow();
		int cell2Col=cell2.getCol();
		
		getMyGrid().setCell(cell1.getRow(), cell1.getCol(), cell2);
		cell2.setRow(cell1.getRow());
		cell2.setCol(cell1.getCol());
		getMyGrid().setCell(cell2Row, cell2Col, cell1);
		cell1.setRow(cell2Row);
		cell1.setCol(cell2Col);
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
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> emptyCells=getClassSpecificSubcells(cellList,"WatorEmpty");
		
		
		if(emptyCells.size()>0){
			Random rn = new Random();
			WatorEmpty targetCell=(WatorEmpty)emptyCells.get(rn.nextInt(emptyCells.size()));
			getMyGrid().setCell(targetCell.getRow(), targetCell.getCol(), creature.makeChild());
		}
		creature.resetTimeSinceBirth();
	}
	
	public void moveIfAble(WatorCreature creature){
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> emptyCells=getClassSpecificSubcells(cellList,"WatorEmpty");
		
		if(emptyCells.size()>0){
			Random rn = new Random();
			WatorEmpty targetCell=(WatorEmpty)emptyCells.get(rn.nextInt(emptyCells.size()));
			getMyGrid().switchCell(targetCell, creature);
		}
	}
	
	public void eatIfAble(WatorPredator creature){
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> preyCells=getClassSpecificSubcells(cellList,"WatorPrey");
		
		if(preyCells.size()>0){
			Random rn = new Random();
			WatorEmpty targetCell=(WatorEmpty)preyCells.get(rn.nextInt(preyCells.size()));
			moveToAndReplace(targetCell.getRow(),targetCell.getCol(), creature);
			creature.resetTimeSinceAte();
		}
	}
	
	public void handleHunger(WatorPredator creature){
		creature.incrementTimeSinceAte();
		if(creature.getTimeSinceAte()==creature.getMaxHungerPeriod()){
			moveToAndReplace(creature.getRow(),creature.getCol(),new WatorEmpty());
		}
	}
	
	
	public List<Cell> getClassSpecificSubcells(List<Cell> list,String className){
		List<Cell> sublist=new ArrayList<>();
		Class<?> cls;
		try {
			cls = Class.forName(className);
			for(Cell item:list){
				if(cls.isInstance(item)){	
					sublist.add(item);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sublist;
	}
	

	@Override
	public void update() {
		List<Cell> allCells=new ArrayList<>();
		BackEndGrid grid=getMyGrid();
		for(int i=0; i<grid.getRows();i++){
			for(int j=0;j<grid.getColumns();j++){
				allCells.add(grid.getCell(i, j));
			}
		}
		
		List<Cell> predators=getClassSpecificSubcells(allCells,"WatorPredator");
		for(Cell predator: predators){
			eatIfAble((WatorPredator)predator);
			handleHunger((WatorPredator)predator);
			moveIfAble((WatorPredator)predator);
			handleReproduction((WatorPredator)predator);
		}
		
		List<Cell> preyCells=getClassSpecificSubcells(allCells,"WatorPrey");
		for(Cell prey: preyCells){
			moveIfAble((WatorPrey)prey);
			handleReproduction((WatorPrey)prey);
		}
	}

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : initialCells.keySet()) {
			String cellType = initialCells.get(coordinates);	
			Class<?> cls;
			try {
				cls = Class.forName(cellType);
				Cell cell=(Cell) cls.newInstance();
				getMyGrid().setCell(coordinates[0],coordinates[1],cell);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}