package Simulations;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;
import Cells.GameOfLifeCell;
import Utils.GameOfLifeParameterParser;

public class GameOfLife extends Simulation{
	
	private static final double probDead = 0.2;
	
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
		for (int i=0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				double rand = Math.random();
				if (rand<probDead) {
					getMyGrid().setCell(i, j, new GameOfLifeCell(DEAD));
				} else {
					getMyGrid().setCell(i, j, new GameOfLifeCell(ALIVE));
				}
			}
		}
	}


	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}