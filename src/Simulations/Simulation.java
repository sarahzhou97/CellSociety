package Simulations;

<<<<<<< HEAD
=======
import default.Grid;

>>>>>>> c318d0d3f02c59c1fb02216b67539c6d2d0045d8
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
