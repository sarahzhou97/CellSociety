package Simulations;

import UI.Grid;

public abstract class Simulation {
	//private final int screenSizeX = 400;
	//private final int screenSizeY = 400;
	
	//private Grid grid;
	private int gridSize;
	private String ID;
	
	public Simulation(int size){
		gridSize = size;
	}
	
	public abstract void updateGrid();
	
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
	
}