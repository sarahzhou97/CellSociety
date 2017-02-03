package Simulations;

import UI.Grid;

public abstract class Simulation {
	//private final int screenSizeX = 400;
	//private final int screenSizeY = 400;
	
	private Grid myGrid;
	private String ID;
	private int myGridSize;
	
	public Simulation(int size){
		setMyGrid(new Grid(size));
	}
	
	public abstract void update();
	
	public abstract void initiateSimulation();
	
	public void play() {
		
	}
	
	public void pause() {
		
	}
	
	public void stop() {
		
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public Grid getMyGrid() {
		return myGrid;
	}

	public void setMyGrid(Grid myGrid) {
		this.myGrid = myGrid;
	}
	
	public int getGridSize() {
		return myGridSize;
	}
	
}