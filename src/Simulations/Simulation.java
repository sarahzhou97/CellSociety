package Simulations;

import cellsociety_team10.Grid;

public abstract class Simulation {
	private final int screenSizeX;
	private final int screenSizeY;
	private Grid simulationGrid;
	
	public Simulation(int dimensionX, int dimensionY) {
		screenSizeX = dimensionX;
		screenSizeY = dimensionY;
	}
	
	public Simulation(Grid grid){
		simulationGrid=grid;
	}
	

	public void updateGrid(){//overriden for each subclass
		
	}
}
