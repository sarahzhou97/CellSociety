package UI;


import java.util.ArrayList;
import java.util.List;

import Cells.Cell;

public class Grid {

	private Cell[][] myCellGrid;

	public Grid(int size) {
		myCellGrid = new Cell[size][size];
	}

	public Cell getCell(int x,int y){
		return myCellGrid[x][y];
	}

	public void switchCell(){

	}

	public void displayGrid(){

	}

	public void setCell(int row, int col, Cell cell) {
		myCellGrid[row][col] = cell;
	}

	public List<Cell> getNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(getCell(row-1,col));
		} if (row<8) {
			neighborList.add(getCell(row+1,col));
		} if (col>0) {
			neighborList.add(getCell(row,col-1));
		} if (col<8) {
			neighborList.add(getCell(row,col+1));
		}
		return neighborList;
	}

}
