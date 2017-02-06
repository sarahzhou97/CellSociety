package Simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Cells.Cell;
import Cells.SegregationCell;
import Utils.SegregationParameterParser;

public class Segregation extends Simulation {
	private final String EMPTY="empty";  //duplicated code
	
	public Segregation(SegregationParameterParser parameters) {
		super(parameters);
	}

	@Override
	public void update() {
		List<SegregationCell> occupiedCells=new ArrayList<>();
		List<SegregationCell> emptyCells=new ArrayList<>();
		for(int i=0;i<getMyGrid().getRows();i++){
			for(int j=0; j<getMyGrid().getColumns();j++){
				SegregationCell cell=(SegregationCell)getMyGrid().getCell(i, j);
				if(cell.getState().equals(EMPTY)){
					emptyCells.add(cell);
				}
			}
		}
		
		for(SegregationCell cell:occupiedCells){
			if(!cell.alwaysSatisfied()){
				List<Cell> cellNeighbors=getMyGrid().getEightNeighbors(cell.getRow(),cell.getCol());
				List<Cell> emptyNeighbors=getStateSpecificSubset(cellNeighbors,EMPTY);
				List<Cell> sameKinds=getStateSpecificSubset(cellNeighbors,cell.getState());
				double occupiedNeighbors=cellNeighbors.size()-emptyNeighbors.size();
				double similarityIndex=occupiedNeighbors/sameKinds.size();
				
				if(similarityIndex<cell.getSatisfactionRequirement()){
					Random rn = new Random();
					int index=rn.nextInt(emptyNeighbors.size());
					getMyGrid().switchCell(cell, emptyNeighbors.get(index));
					
				}
			}
		}
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