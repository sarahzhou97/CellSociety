package Simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import BackEndGrid.BackEndGrid;
import Cells.Cell;
import Cells_Wator.WatorCreature;
import Cells_Wator.WatorEmpty;
import Cells_Wator.WatorPredator;
import Cells_Wator.WatorPrey;

public class PredatorPrey extends Simulation {
	private int PREY_GESTATION_PERIOD=5;
	private int PREDATOR_GESTATION_PERIOD=10;
	private int starveTime=5;
	
	public PredatorPrey(Map<String,String> parameters,Map<int[],String> cells) {
		super(parameters,cells);
	}
	
	public void moveToAndReplace(int x, int y,Cell creature){//Replaces target cell and leaves blank cell behind
		getMyGrid().setCell(creature.getRow(), creature.getCol(), new WatorEmpty());
		getMyGrid().setCell(x,y,creature);
	}
	
	public void handleReproduction(WatorPredator creature){//duplicate code, i know, but it removes typecasting code smell
		if(PREDATOR_GESTATION_PERIOD==creature.getTimeSinceBirth()){
			birthIfAble(creature);
		}
		else{
			creature.incrementTimeSinceBirth();
		}
	}
	
	public void handleReproduction(WatorPrey creature){
		if(PREY_GESTATION_PERIOD==creature.getTimeSinceBirth()){
			birthIfAble(creature);
		}
		else{
			creature.incrementTimeSinceBirth();
		}
	}
	
	public void birthIfAble(WatorCreature creature){
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> emptyCells=getClassSpecificSubcells(cellList,"Cells_Wator.WatorEmpty");		
		
		if(emptyCells.size()>0){
			Random rn = new Random();
			WatorEmpty targetCell=(WatorEmpty)emptyCells.get(rn.nextInt(emptyCells.size()));
			getMyGrid().setCell(targetCell.getRow(), targetCell.getCol(), creature.makeChild());
			creature.resetTimeSinceBirth();
		}
	}
	
	public void moveIfAble(WatorCreature creature){
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> emptyCells=getClassSpecificSubcells(cellList,"Cells_Wator.WatorEmpty");
		
		if(emptyCells.size()>0){
			Random rn = new Random();
			WatorEmpty targetCell=(WatorEmpty)emptyCells.get(rn.nextInt(emptyCells.size()));
			getMyGrid().switchCell(targetCell, creature);
		}
	}
	
	public void eatIfAble(WatorPredator creature){
		List<Cell> cellList=getMyGrid().getEightNeighbors(creature.getRow(), creature.getCol());
		List<Cell> preyCells=getClassSpecificSubcells(cellList,"Cells_Wator.WatorPrey");
		
		if(preyCells.size()>0){
			Random rn = new Random();
			WatorPrey targetCell=(WatorPrey)preyCells.get(rn.nextInt(preyCells.size()));
			moveToAndReplace(targetCell.getRow(),targetCell.getCol(), creature);
			creature.resetTimeSinceAte();
			System.out.println(creature.getRow());
		}
	}
	
	public void handleHunger(WatorPredator creature){
		creature.incrementTimeSinceAte();
		if(creature.getTimeSinceAte()>=starveTime){
			getMyGrid().setCell(creature.getRow(),creature.getCol(),new WatorEmpty());
		}
	}	

	@Override
	public void update() {
		List<Cell> allCells = getAllCells();
		List<Cell> predators=getClassSpecificSubcells(allCells,"Cells_Wator.WatorPredator");
		for(Cell predator: predators){
			
			eatIfAble((WatorPredator)predator);
			
			if(((WatorPredator)predator).getTimeSinceAte()>0){
				moveIfAble((WatorPredator)predator);			
			}
			handleReproduction((WatorPredator)predator);
			handleHunger((WatorPredator)predator);
		}
		
		List<Cell> allCellsAfterPredatorAction=getAllCells();
		List<Cell> preyCells=getClassSpecificSubcells(allCellsAfterPredatorAction,"Cells_Wator.WatorPrey");
		for(Cell prey: preyCells){
			moveIfAble((WatorPrey)prey);
			handleReproduction((WatorPrey)prey);
		}

	}

	private List<Cell> getAllCells() {
		List<Cell> allCells=new ArrayList<>();
		BackEndGrid grid=getMyGrid();
		for(int i=0; i<grid.getRows();i++){
			for(int j=0;j<grid.getColumns();j++){
				allCells.add(grid.tryGetCell(i, j));
			}
		}
		return allCells;
	}
	
	private int getWatorClassCount(String className){
		List<Cell> allCells=getAllCells();
		List<Cell> wantedCells=getClassSpecificSubcells(allCells,"Cells_Wator."+className);
		return wantedCells.size();
	}

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : getMyCells().keySet()) {
			String cellType = getMyCells().get(coordinates);	
			Class<?> cls;
			try {
				cls = Class.forName("Cells_Wator."+cellType);
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