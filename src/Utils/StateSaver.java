package Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StateSaver {
	
	private Map<String,String> myParameters;
	private Map<int[], String> myCells;
	private String mySimType;
	private PrintWriter myWriter;
	private String myFileName;

	public StateSaver() {
	}
	
	public StateSaver(String fileName, String simType,Map<String,String> parameters,Map<int[], String> cells) {
		myFileName = fileName;
		myParameters = parameters;
		myCells = cells;
		mySimType = simType;
		writeToXML();
	}
	
	public void writeToXML() {
		try{
		    myWriter = new PrintWriter(myFileName, "UTF-8");
		    myWriter.println("<simulation id="+"\""+mySimType+"\""+">");
		    writeParameters();
		    writeCells();
		    myWriter.println("</simulation>");
		    myWriter.close();
		} catch (IOException e) {
		   // do something
		}
	} 
	
	private void writeParameters() {
		for (String parameter : myParameters.keySet()) {
			myWriter.println("	<"+parameter+">"+myParameters.get(parameter)+"</"+parameter+">");
		}
	}
	
	private void writeCells() {
		myWriter.println("	<cells>");
		for (int[] coordinates : myCells.keySet()) {
			myWriter.println("		<cell>"+coordinates[0]+","+coordinates[1]+myCells.get(coordinates)+"</cell>");
		}
		myWriter.println("	</cells");
	}

	public void setParameters(Map<String,String> parameters) {
		myParameters = parameters;
	}
	
	public void setCells(Map<int[], String> cells) {
		myCells = cells;
	}
	
	public void setSimType(String simType) {
		mySimType = simType;
	}
}
