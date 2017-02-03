

import java.awt.Point;

public abstract class Cell {
	private int type;
	private Point myPoint;
	
	public int getType(){
		return type;
	}
	
	public Point getPoint(){
		return myPoint;
	}
	
	public void respondToNeighbours(){//used only for conway and fire
		
	}
	
	

}
