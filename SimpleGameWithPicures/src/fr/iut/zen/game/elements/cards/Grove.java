package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card {
	final String grovePATH = "pictures/GroveCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
	}

	@Override
	public Tile getTile() {
		
		return null;
	}
}
