package BackEndGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cells.Cell;

public class SquareFourNeighborGrid extends BackEndGrid{

	public SquareFourNeighborGrid(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Cell> getNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(tryGetCell(row-1,col));
		} if (row<getRows()-1) {
			neighborList.add(tryGetCell(row+1,col));
		} if (col>0) {
			neighborList.add(tryGetCell(row,col-1));
		} if (col<getColumns()-1) {
			neighborList.add(tryGetCell(row,col+1));
		}
		neighborList.removeAll(Collections.singleton(null));
		return neighborList;
		
	}

}
