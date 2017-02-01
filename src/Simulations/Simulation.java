package Simulations;

import Grid;

public abstract class Simulation {
	private final int screenSizeX = 400;
	private final int screenSizeY = 400;
	private Grid grid;
	
	public Simulation(Grid grid){
		this.grid = grid;
	}
	
	public abstract void updateGrid();
	
	public void play() {
		
	}
	
	public void pause() {
		
	}
	
	public void stop() {
		
	}
	
}
