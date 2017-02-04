package UI;


import java.util.ArrayList;
import java.util.List;

import Cells.Cell;
import Utils.FileReader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
	private double canvasWidth=200;
	private double canvasHeight=200;
	private int sceneWidth=300;
	private int sceneHeight=300;
	private Cell[][] myCellGrid;
	private int columns;
	private int rows;
	private FileReader myFileReader;
	private Canvas gridPicture;
	private Scene gridScene;

	public Grid(int size) {
		myCellGrid = new Cell[size][size];
		myFileReader = new FileReader();
		this.columns=size;
		this.rows=size;
		Group root=new Group();
		root.getChildren().add(gridPicture);
		gridPicture=new Canvas(canvasWidth,canvasHeight);
		displayGrid();
		gridScene=new Scene(root,sceneWidth,sceneHeight,Color.WHITE);
	}

	public Cell getCell(int x,int y){
		return myCellGrid[x][y];
	}

	public void switchCell(){

	}

	public void displayGrid(){
		GraphicsContext gc=gridPicture.getGraphicsContext2D();
		gc.setFill(Color.BLUE);//Fill in unoccupied grid spaces with a default color
		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		double cellWidth=canvasWidth/columns;
		double cellHeight=canvasHeight/rows;
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
				gc.fillRect(i*cellWidth, j*cellHeight, (i+1)*cellWidth, (j+1)*cellHeight);
			}
		}
	}
	
	public Scene getScene(){
		return gridScene;
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
