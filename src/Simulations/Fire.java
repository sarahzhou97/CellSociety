package Simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Cells.Cell;
import Cells.FireCell;
import Utils.FireParameterParser;

public class Fire extends Simulation {

	private double probCatch;

	private boolean stop;

	private static final String EMPTY = "empty";
	private static final String TREE = "tree";
	private static final String BURNING = "burning";
	
	private List<FireCell> burningList;
	private List<FireCell> emptyList;

	public Fire(FireParameterParser parameters) {
		super(parameters);
		stop = false;
		probCatch = parameters.getProbCatch();
		burningList = new ArrayList<FireCell>();
		emptyList = new ArrayList<FireCell>();
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
	
	private void stop() {
		stop= true;
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
		FireCell cell = (FireCell) getMyGrid().getCell(row, col);
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
		for (FireCell cell : burningList) {
			cell.updateState(BURNING);
		}
		for (FireCell cell : emptyList) {
			cell.updateState(EMPTY);
		}
		burningList.clear();
		emptyList.clear();
	}
	
	public void initiateSimulation() {
		for (int[] coordinates : initialCells.keySet()) {
			String cellType = initialCells.get(coordinates);
			FireCell cell = null;
			if (cellType.equals(BURNING)) {
				cell = new FireCell(BURNING);
			} else if (cellType.equals(EMPTY)) {
				cell = new FireCell(EMPTY);
			} else if (cellType.equals(TREE)) {
				cell = new FireCell(TREE);
			}
			getMyGrid().setCell(coordinates[0],coordinates[1],cell);
		}
	}

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}