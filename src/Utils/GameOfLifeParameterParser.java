package Utils;

import org.w3c.dom.Element;

public class GameOfLifeParameterParser extends ParameterParser {
	
	private final String GAME_OF_LIFE_ID = "GameOfLife";
	private String gameOfLifeTitle;

	public GameOfLifeParameterParser(String fileName) {
		super(fileName);
	}

	@Override
	protected void setParameters(Element el) {
		gameOfLifeTitle = el.getElementsByTagName("title").item(0).getTextContent();
		
	}

	@Override
	public String getTitle() {
		return gameOfLifeTitle;
	}
	
	

}
