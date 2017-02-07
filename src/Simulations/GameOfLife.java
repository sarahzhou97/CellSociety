package Simulations;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;
import Cells.FireCell;
import Cells.GameOfLifeCell;
import Utils.GameOfLifeParameterParser;

public class GameOfLife extends Simulation{
	
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";

	public GameOfLife(GameOfLifeParameterParser parameters) {
		super(parameters);
	}
	
	@Override
	public void update(){
		for (int i=0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				applyRulesToCell(i,j);
			}
			}
	}
	
	private void applyRulesToCell(int row, int col) {
		GameOfLifeCell cell = (GameOfLifeCell) getMyGrid().getCell(row, col);
		List<Cell> neighbors = getMyGrid().getEightNeighbors(row, col);
		int numAliveNeighbors=0;
		for (Cell gameCell : neighbors) {
			if (gameCell.getState().equals(ALIVE)) {
				numAliveNeighbors++;
			}
		}
		if (cell.getState().equals(ALIVE)) {
			if (numAliveNeighbors<2||numAliveNeighbors>3) {
				cell.updateState(DEAD);
				return;
			}
		} else if (cell.getState().equals(DEAD)) {
			if (numAliveNeighbors==3) {
				cell.updateState(ALIVE);
				return;
			}
		}
	}

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : initialCells.keySet()) {
			String cellType = initialCells.get(coordinates);
			GameOfLifeCell cell = null;
			if (cellType.equals(DEAD)) {
				cell = new GameOfLifeCell(DEAD);
			} else if (cellType.equals(ALIVE)) {
				cell = new GameOfLifeCell(ALIVE);
			}
			getMyGrid().setCell(coordinates[0],coordinates[1],cell);
		}
	}


	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}