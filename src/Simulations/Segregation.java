package Simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Cells.Cell;
import Cells.GameOfLifeCell;
import Cells.SegregationCell;
import Utils.SegregationParameterParser;


public class Segregation extends Simulation {
	private final String EMPTY="empty";  //duplicated code
	private final String TYPE1="type1";
	private final String TYPE2="type2";

	public Segregation(SegregationParameterParser parameters) {
		super(parameters);
	}


	@Override
	public void update() {
		System.out.print("called update");
		List<SegregationCell> occupiedCells=new ArrayList<>();
		List<SegregationCell> emptyCells=new ArrayList<>();
		for(int i=0;i<getMyGrid().getRows();i++){
			for(int j=0; j<getMyGrid().getColumns();j++){
				SegregationCell cell=(SegregationCell)getMyGrid().getCell(i, j);
				if(cell.getState().equals(EMPTY)){
					emptyCells.add(cell);
				}else{
					occupiedCells.add(cell);
				}
			}
		}
		
		for(SegregationCell cell:occupiedCells){
			if(!cell.alwaysSatisfied()){
				
				List<Cell> cellNeighbors=getMyGrid().getEightNeighbors(cell.getRow(),cell.getCol());
				System.out.println("cellNeighbors:"+cellNeighbors.size());
				List<Cell> emptyNeighbors=getStateSpecificSubset(cellNeighbors,EMPTY);
				System.out.println("emptyNeighbors:"+emptyNeighbors.size());
				List<Cell> similarNeighbors=getStateSpecificSubset(cellNeighbors,cell.getState());
				System.out.println("similarNeighbors:"+similarNeighbors.size());
				double occupiedNeighbors=cellNeighbors.size()-emptyNeighbors.size();
				double numberOfSimilarNeighbors=similarNeighbors.size();
				double similarityIndex=numberOfSimilarNeighbors/occupiedNeighbors;
				
				System.out.println("occupiedNeighbors"+occupiedNeighbors);
				System.out.println("num same kind"+numberOfSimilarNeighbors);
				System.out.println("Similarity:"+similarityIndex);
				System.out.println("Needed:"+cell.getSatisfactionRequirement());
				if(similarityIndex<cell.getSatisfactionRequirement()){
					Random rn = new Random();
					int index=rn.nextInt(emptyCells.size());
					getMyGrid().switchCell(cell, emptyCells.get(index));
					System.out.print("attempted switch");
				}
			}
		}
	}
	
	

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : initialCells.keySet()) {
			String cellType = initialCells.get(coordinates);	
			SegregationCell cell = null;
			if (cellType.equals(TYPE1)) {
				cell = new SegregationCell(TYPE1);
			} else if (cellType.equals(TYPE2)) {
				cell = new SegregationCell(TYPE2);
			}else if (cellType.equals(EMPTY)){
				cell=new SegregationCell(EMPTY);
			}else{
				System.out.print(cellType);
			}
			getMyGrid().setCell(coordinates[0],coordinates[1],cell);
			cell.setRow(coordinates[0]);
			cell.setCol(coordinates[1]);
		}
	}

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}