package UI_FrontEndGrid;

import BackEndGrid.BackEndGrid;
import Cells.Cell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SugarGridDisplay extends BaseFrontEndGrid{
	public SugarGridDisplay(BackEndGrid myGrid, double canvasWidth, double canvasHeight, 
			Color defaultColor/*,HashMap<String, Color> cellColors*/) {
			super(myGrid,canvasWidth,canvasHeight,defaultColor);
		}
	
	public void drawCell(double x, double y, Cell cell, boolean edgeUpwards, GraphicsContext gc){
		//Change color of cell depending on how much sugar it contains.
		super.drawCell(x, y, cell, edgeUpwards, gc);
		//If cell contains a person, draw a circle in the center of the cell.
	}
}
