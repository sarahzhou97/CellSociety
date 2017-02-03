package Simulations;

import java.util.List;

import Cells.Cell;
import Cells.FireCell;
import UI.Grid;

public class Fire extends Simulation {
	
	private double probCatch;
	
	private boolean isBurningTree;
	
	private static final String EMPTY = "empty";
	private static final String TREE = "tree";
	private static final String BURNING = "burning";

	public Fire(int size, double probCatch) {
		super(size);
		setID("Fire");
		this.probCatch = probCatch;
	}

	@Override
	public void update() {
		isBurningTree = false;
		for (int i = 0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				applyRules(i,j);
			}
		}
		if (isBurningTree) {
			getMyGrid().displayGrid();
		} else {
			stop();
		}
	}
	
	private void applyRules(int row, int col) {
		FireCell cell = (FireCell) getMyGrid().getCell(row, col);
		if (cell.getState().equals(BURNING)) {
			isBurningTree = true;
			cell.setState(EMPTY);
			List<Cell> neighbors = getMyGrid().getNeighbors(row,col);
			for (Cell neighborCell : neighbors) {
				if (neighborCell.getState().equals(TREE)) {
					calculateNewStateOfTree((FireCell) neighborCell);
				}
			}
		}
	}
	
	private void calculateNewStateOfTree(FireCell cell) {
		double random = Math.random();
		if (random<probCatch) {
			cell.updateState(BURNING);
		}
	}

	@Override
	public void initiateSimulation() {
		
		int gridEdge = getGridSize()-1;
		int mid = getGridSize()/2;
		
		for (int i=0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				if (i==0 || i==gridEdge) {
					getMyGrid().setCell(i, j, new FireCell(EMPTY));
				} else {
					if (j==mid && i==mid) {
						getMyGrid().setCell(i, j, new FireCell(BURNING));
					} else if (j==gridEdge || j==0) {
						getMyGrid().setCell(i, j, new FireCell(EMPTY));
					} else {
						getMyGrid().setCell(i, j, new FireCell(TREE));
					}
				}
			}
			
		}

	}

}