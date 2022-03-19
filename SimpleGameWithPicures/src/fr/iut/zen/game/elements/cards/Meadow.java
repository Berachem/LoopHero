package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Meadow implements Card {
	final String meadowPATH = "pictures/MeadowCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return meadowPATH;
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return null;
	}
}
