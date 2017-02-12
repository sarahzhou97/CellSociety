package UI_FrontEndGrid;

import java.util.HashMap;

import BackEndGrid.BackEndGrid;
import Cells.Cell;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author Daniel
 *	Stores a node that represents the grid of Cells in the BackEndGrid that can be returned when called.
 */
public class BaseFrontEndGrid { 
	private double canvasWidth;
	private double canvasHeight;
	private Canvas gridPicture;
	private BackEndGrid myGrid;
	private Color defaultColor;
	private String latticeType="square";
	private HashMap<String,Color> cellColors;//Colors a cell based on its getState
	private final String SQUARE_LATTICE="square";
	private final String HEXAGON_LATTICE="hexagon";
	private final String TRIANGLE_LATTICE="triangle";
	private boolean drawCellBorders;
	private double cellEdgeLength=60.0;

	public BaseFrontEndGrid(BackEndGrid myGrid, double canvasWidth, double canvasHeight, 
		Color defaultColor/*,HashMap<String, Color> cellColors*/) {
		this.defaultColor=defaultColor;//potential to crash and burn
		this.myGrid=myGrid;
		this.canvasHeight=canvasHeight;
		this.canvasWidth=canvasWidth;
		System.out.print(canvasWidth);
		gridPicture=new Canvas(canvasWidth,canvasHeight);
	}
	
	public void drawCell(double x, double y, Cell cell, boolean edgeUpwards, GraphicsContext gc){
		if(latticeType==SQUARE_LATTICE){
			gc.setFill(cell.getColor());
			//gc.setFill(cellColors.get(cell.getState()));
			gc.fillRect(x, y, x+cellEdgeLength, y+cellEdgeLength);
		}
		
		if(latticeType==HEXAGON_LATTICE){
			drawHexagonCell(x, y, cell, gc);
		}
		
		if(latticeType==TRIANGLE_LATTICE){
			drawTriangleCell(x, y, cell, edgeUpwards, gc);
		}
	}

	private void drawHexagonCell(double x, double y, Cell cell, GraphicsContext gc) {
		gc.setFill(cell.getColor());
		//gc.setFill(cellColors.get(cell.getState()));
		double[] xPoints=new double[6];
		double[] yPoints=new double[6];
		xPoints[0]=x+cellEdgeLength/2;
		xPoints[1]=x+3*cellEdgeLength/2;
		xPoints[2]=x+2*cellEdgeLength;
		xPoints[3]=x+3*cellEdgeLength/2;
		xPoints[4]=x+cellEdgeLength/2;
		xPoints[5]=x;
		yPoints[0]=y;
		yPoints[1]=y;
		yPoints[2]=y+cellEdgeLength*Math.sqrt(3)/2;
		yPoints[3]=y+cellEdgeLength*Math.sqrt(3);
		yPoints[4]=y+cellEdgeLength*Math.sqrt(3);
		yPoints[5]=y+cellEdgeLength*Math.sqrt(3)/2;
		gc.fillPolygon(xPoints, yPoints, 6);
	}

	private void drawTriangleCell(double x, double y, Cell cell, boolean edgeUpwards, GraphicsContext gc) {
		gc.setFill(cell.getColor());
		//gc.setFill(cellColors.get(cell.getState()));
		double[] xPoints=new double[3];
		double[] yPoints=new double[3];
		if(edgeUpwards==true){
			xPoints[0]=x;
			xPoints[1]=x+cellEdgeLength;
			xPoints[2]=x+cellEdgeLength/2;
			yPoints[0]=y;
			yPoints[1]=y;
			yPoints[2]=y+Math.sqrt(3)*cellEdgeLength/2;
		}
		else{
			xPoints[0]=x+cellEdgeLength/2;
			xPoints[1]=x+cellEdgeLength;
			xPoints[2]=x;
			yPoints[0]=y;
			yPoints[1]=y+Math.sqrt(3)*cellEdgeLength/2;
			yPoints[2]=y+Math.sqrt(3)*cellEdgeLength/2;
		}
		for(int i=0;i<xPoints.length;i++){
			xPoints[i]=xPoints[i]+1;
			yPoints[i]=yPoints[i]+1;
			System.out.println(xPoints[i]+","+yPoints[i]);
		}
		
		gc.fillPolygon(xPoints, yPoints, 3);
	}
	
	public void displayCellBorders(boolean choice){
		drawCellBorders=choice;
	}
	
	public void updateCanvasDimensions(){
		
	}
	
	public void setCellWidth(){
		
	}
	
	public void setCellHeight(){
		
	}

	public void updateGrid(){
		if(latticeType==SQUARE_LATTICE)updateSquareGrid();
		if(latticeType==TRIANGLE_LATTICE)updateTriangleGrid();
		if(latticeType==HEXAGON_LATTICE)updateHexagonGrid();
	}

	private void updateSquareGrid() {
		GraphicsContext gc=gridPicture.getGraphicsContext2D();
		gc.setFill(defaultColor);
		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		
		int columns=myGrid.getColumns();
		int rows=myGrid.getRows();
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
				Color javaColor=myGrid.tryGetCell(i,j).getColor();
				gc.setFill(javaColor);
				gc.fillRect(i*cellEdgeLength, j*cellEdgeLength, (i+1)*cellEdgeLength, (j+1)*cellEdgeLength);
			}
		}
	}
	
	private void updateTriangleGrid(){
		GraphicsContext gc=gridPicture.getGraphicsContext2D();
		gc.setFill(defaultColor);
		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		for(int i=0;i<myGrid.getRows();i++){
			for(int j=0;j<myGrid.getColumns();j++){
				Cell cell=myGrid.getCell(i, j);
				boolean edgeUpwards=false;//don't think this line is necessary but just in case
				if((i+j)%2==0) edgeUpwards=true;
				drawCell(j*0.5*cellEdgeLength,i*cellEdgeLength*Math.sqrt(3)/2,cell,edgeUpwards,gc);
			}
		}
	}	
	
	private void updateHexagonGrid(){
		GraphicsContext gc=gridPicture.getGraphicsContext2D();
		gc.setFill(defaultColor);
		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		for(int i=0;i<myGrid.getRows();i++){
			for(int j=0;j<myGrid.getColumns();j++){
				Cell cell=myGrid.getCell(i, j);
				if(i%2==0){
					drawCell(j*3*cellEdgeLength,i*(Math.sqrt(3)/2)*cellEdgeLength,cell,false,gc);
				}
				else{
					drawCell(j*3*cellEdgeLength+1.5*cellEdgeLength,i*(Math.sqrt(3)/2)*cellEdgeLength,cell,false,gc);
				}
			}
		}
	}
	
	public Node returnDisplay(){
		return gridPicture;		
	}

}