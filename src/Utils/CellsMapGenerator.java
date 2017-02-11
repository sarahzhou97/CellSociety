package Utils;

import java.util.Map;

public class CellsMapGenerator {
	
	private final static String PROBABILITY = "probability";
	private final static String NUMBER = "number";
	
	private final static String FIRE = "probability";
	private final static String GAME_OF_LIFE = "number";
	private final static String SEGREGATION = "probability";
	private final static String PREDATOR_PREY = "number";

	public CellsMapGenerator(String method,String simType) {
	}
	
	public Map<int[],String> generateMap(String method,String simType) {
		if (simType.equals(FIRE)) {
			return generateFireMap(method);
		} else if (simType.equals(GAME_OF_LIFE)) {
			return generateGameOfLifeMap(method);
		} else if (simType.equals(PREDATOR_PREY)) {
			return generateFireMap(method);
		} else if (simType.equals(SEGREGATION)) {
			return generateSegregationMap(method);
		} else {
			return null;
		}
	}
	
	private Map<int[], String> generateFireMap(String method) {
		return null;
	}
	
	private Map<int[], String> generateGameOfLifeMap(String method) {
		return null;
	}
	
	private Map<int[], String> generateSegregationMap(String method) {
		return null;
	}
	
	private Map<int[], String> generatePredatorPreyMap(String method) {
		return null;
	}
}
