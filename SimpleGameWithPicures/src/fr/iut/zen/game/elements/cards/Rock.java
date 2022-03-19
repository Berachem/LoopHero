package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Rock implements Card {
	final String rockPATH = "pictures/RockCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return rockPATH;
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return null;
	}
}
