package BackEndGrid;


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Cells.Cell;

public class BackEndGrid {
	private Cell[][] myCellGrid;
	private int maxColumns;
	private int maxRows;
	private final int growthRange=5;
	private boolean infinite=true;
	private boolean toiroidal;

	public BackEndGrid(int size) {
		myCellGrid = new Cell[size][size];
		//myFileReader = new FileReader();//commented this because it caused compile errors, but may be put back in later
		this.maxColumns=size;
		this.maxRows=size;
	}
//needs work
	public Cell getCell(int x,int y){
		if(toiroidal) return myCellGrid[x%maxRows][y%maxColumns];
		else return myCellGrid[x][y];
	}
	
	public int getRows(){
		return maxRows;
	}
	
	public int getColumns(){
		return maxColumns;
	}

	
	public void switchCell(Cell cell1, Cell cell2){
		int cell2Row=cell2.getRow();
		int cell2Col=cell2.getCol();
		
		setCell(cell1.getRow(), cell1.getCol(), cell2);
		cell2.setRow(cell1.getRow());
		cell2.setCol(cell1.getCol());
		setCell(cell2Row, cell2Col, cell1);
		cell1.setRow(cell2Row);
		cell1.setCol(cell2Col);
	}

	public void setCell(int row, int col, Cell cell) {
		myCellGrid[row][col] = cell;
		cell.setCol(col);
		cell.setRow(row);
		if(infinite){
			expandIfNeeded(row, col, cell);
		}
	}
	private void expandIfNeeded(int row, int col, Cell cell) {
		if(cell.getState().equals("empty")){
			if(col<=growthRange) growArray(0,-maxColumns,cell);
			if(col>=maxColumns-growthRange) growArray(0,maxColumns,cell);
			if(row<=growthRange) growArray(-maxRows,0,cell);
			if(row>=maxRows-growthRange) growArray(maxRows,0,cell);
		}
	}
	
	public void growArray(int rowDisplacement,int columnDisplacement, Cell toolCell){
		Cell[][] newCellGrid=new Cell[maxRows+rowDisplacement][maxColumns+columnDisplacement];
		for(int i=0;i<newCellGrid.length;i++){
			for(int j=0;j<newCellGrid[0].length;j++){
				Cell emptyCell=toolCell.getEmptyCell();
				newCellGrid[i][j]=emptyCell;
				emptyCell.setRow(i);
				emptyCell.setCol(j);
			}
		}
		
		int startRow=0;
		int startColumn=0;
		if(rowDisplacement<0) startRow=Math.abs(rowDisplacement);
		if(columnDisplacement<0) startColumn=Math.abs(columnDisplacement);
		for(int m=0;m<myCellGrid.length;m++){
			for(int n=0;m<myCellGrid[0].length;n++){
				newCellGrid[m+startRow][n+startColumn]=myCellGrid[m][n];
				newCellGrid[m+startRow][n+startColumn].setRow(m+startRow);
				newCellGrid[m+startRow][n+startColumn].setRow(n+startColumn);
			}
		}
		myCellGrid=newCellGrid;
		maxRows+=Math.abs(rowDisplacement);
		maxColumns+=Math.abs(columnDisplacement);
	}
	
	public List<Cell> getFourNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(getCell(row-1,col));
		} if (row<maxRows-1) {
			neighborList.add(getCell(row+1,col));
		} if (col>0) {
			neighborList.add(getCell(row,col-1));
		} if (col<maxColumns-1) {
			neighborList.add(getCell(row,col+1));
		}
		return neighborList;
	}
	
	
	public List<Cell> getEightNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.addAll(getFourNeighbors(row, col));
		if (row>0&&col>0) {
			neighborList.add(getCell(row-1,col-1));
		} if (row>0&&col<myCellGrid.length-1) {
			neighborList.add(getCell(row-1,col+1));
		} if (row<myCellGrid.length-1&&col>0) {
			neighborList.add(getCell(row+1,col-1));
		} if (row<myCellGrid.length-1&&col<myCellGrid.length-1) {
			neighborList.add(getCell(row+1,col+1));
		}
		return neighborList;
	}
	
	public List<Cell> getTriangularNeighbors
}
