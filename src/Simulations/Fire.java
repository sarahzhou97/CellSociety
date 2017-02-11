package Simulations;

import java.util.ArrayList;	
import java.util.List;
import java.util.Map;

import Cells.Cell;
import Cells.FireCell;

public class Fire extends Simulation {

	private double probCatch;

	private boolean stop;

	private static final String EMPTY = "empty";
	private static final String TREE = "tree";
	private static final String BURNING = "burning";
	
	private List<Cell> burningList;
	private List<Cell> emptyList;

	public Fire(Map<String,String> parameters, ) {
		super(parameters);
		stop = false;
		probCatch = Double.parseDouble(parameters.get("probCatch"));
		burningList = new ArrayList<Cell>();
		emptyList = new ArrayList<Cell>();
	}

	@Override
	public void update() {
		if (!burningTreesLeft()) {
			stop();
			return;
		}
		for (int i = 0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				applyRules(i,j);
			}
		}
		updateTrees();
	}
	
	public boolean getStop() {
		return stop;
	}
	
	private boolean burningTreesLeft() {
		int gridSize = getGridSize();
		for (int i=0; i<gridSize;i++) {
			for (int j = 0; j<gridSize;j++) {
				if (getMyGrid().getCell(i, j).getState().equals(BURNING)) {
					return true;
				}
			}
	}
		return false;
	}

	private void applyRules(int row, int col) {
		Cell cell = getMyGrid().getCell(row, col);
		if (cell.getState().equals(TREE)) {
			if (existsBurningNeighbor(getMyGrid().getFourNeighbors(row,col))) {
				calculateNewStateOfTree((FireCell) cell);
			}
		} if (cell.getState().equals(BURNING)) {
			emptyList.add(cell);
		}
	}

	private boolean existsBurningNeighbor(List<Cell> neighbors) {
		for (Cell neighborCell : neighbors) {
			if (neighborCell.getState().equals(BURNING)) {
				return true;
			} 
		}
		return false;
	}

	private void calculateNewStateOfTree(FireCell cell) {
		double random = Math.random();
		if (random<probCatch) {
			burningList.add(cell);
		}
	}
	
	private void updateTrees() {
		for (Cell cell : burningList) {
			cell.updateState(BURNING);
		}
		for (Cell cell : emptyList) {
			cell.updateState(EMPTY);
		}
		burningList.clear();
		emptyList.clear();
	}
	
	public void initiateSimulation() {
		for (int[] coordinates : initialCells.keySet()) {
			String cellType = initialCells.get(coordinates);
			FireCell cell = null;
			cell = new FireCell(cellType);
			getMyGrid().setCell(coordinates[0],coordinates[1],cell);
		}
//			int gridSize = getGridSize();
//			for (int i=0; i<gridSize;i++) {
//				for (int j = 0; j<gridSize;j++) {
//					if (isBorderCell(i,j,gridSize)) {
//						setEmptyCell(i,j);
//					} else if (isMiddleCell(i,j,gridSize)){
//						setBurningCell(i,j);
//					} else {
//						setTreeCell(i,j);
//					}
//				}
//			}
	}
	
	private boolean isBorderCell(int row, int col, int gridSize) {
		int gridEdge = gridSize -1;
		if (row==0 || col==gridEdge || row==0 || col==gridEdge) return true;
		else return false;
	}
	
	private void setEmptyCell(int row, int col) {
		getMyGrid().setCell(row, col, new FireCell(EMPTY));
	}
	
	private boolean isMiddleCell(int row, int col, int gridSize) {
		int mid = gridSize/2;
		if (col==mid && row==mid) return true;
		else return false;
	}
	
	private void setBurningCell(int i, int j) {
		getMyGrid().setCell(i, j, new FireCell(BURNING));
	}
	
	private void setTreeCell(int row, int col) {
		getMyGrid().setCell(row, col, new FireCell(TREE));
	}
	
	public void setProbCatch(double probCatch) {
		this.probCatch=probCatch;
	}

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}


}