package UI;


import java.util.ArrayList;
import java.util.List;

import Cells.Cell;
import Utils.FileReader;

public class Grid {

	private Cell[][] myCellGrid;
	private FileReader myFileReader;

	public Grid(int size) {
		myCellGrid = new Cell[size][size];
		myFileReader = new FileReader();
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
	
	public List<Cell> getFourNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(getCell(row-1,col));
		} if (row<myCellGrid.length) {
			neighborList.add(getCell(row+1,col));
		} if (col>0) {
			neighborList.add(getCell(row,col-1));
		} if (col<myCellGrid.length) {
			neighborList.add(getCell(row,col+1));
		}
		
		return neighborList;
	}
	
	public List<Cell> getEightNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.addAll(getFourNeighbors(row, col));
		if (row>0&&col>0) {
			neighborList.add(getCell(row-1,col-1));
		} if (row>0&&col<myCellGrid.length) {
			neighborList.add(getCell(row-1,col+1));
		} if (row<myCellGrid.length&&col>0) {
			neighborList.add(getCell(row+1,col-1));
		} if (row<myCellGrid.length&&col<myCellGrid.length) {
			neighborList.add(getCell(row+1,col+1));
		}
		return neighborList;
	}

}
