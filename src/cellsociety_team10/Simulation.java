package cellsociety_team10;

public abstract class Simulation {
	private int screenSizeX;
	private int screenSizeY;
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
