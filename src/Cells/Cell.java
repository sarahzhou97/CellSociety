package Cells;

import java.awt.Point;

public abstract class Cell {
	private String state;
	private int row;
	private int col;
	
	public String getState(){
		return state;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	
	public void respondToNeighbours(){//used only for conway and fire
		
	}
	
	public void updateState(String newState) {
		state = newState;
	}
	
	

}
