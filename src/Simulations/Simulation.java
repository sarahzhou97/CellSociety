package Simulations;

import java.util.ArrayList;
import java.util.List;

import BackEndGrid.BackEndGrid;
import Cells.Cell;

public abstract class Simulation {
	//private final int screenSizeX = 400;
	//private final int screenSizeY = 400;
	
	private BackEndGrid myGrid;
	private int myGridSize;
	private String myTitle;
	
	public Simulation(int size,String title){
		myGridSize = size;
		setMyGrid(new BackEndGrid(size));
		myTitle = title;
	}
	
	public abstract void update();
	
	public abstract void initiateSimulation();
	
	public abstract void calculateStatus();
	
	public List<Cell> getStateSpecificSubset(List<Cell> cells, String state){
		List<Cell> sublist=new ArrayList<Cell>();
		for(Cell cell:cells){
			if(cell.getState().equals(state)){
				sublist.add(cell);
			}
		}
		return sublist;
	}
	
	public void play() {
		
	}
	
	public void pause() {
		
	}
	
	public void stop() {
		
	}
	
	public void switchCell(){
		
	}
	
	public void moveToAndReplaceCell(){
		
	}

	public BackEndGrid getMyGrid() {
		return myGrid;
	}

	public void setMyGrid(BackEndGrid myGrid) {
		this.myGrid = myGrid;
	}
	
	public int getGridSize() {
		return myGridSize;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
}