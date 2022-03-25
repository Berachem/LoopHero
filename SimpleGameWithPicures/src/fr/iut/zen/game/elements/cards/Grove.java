package fr.iut.zen.game.elements.cards;

import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card {
	final String grovePATH = "pictures/GroveCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
	}


	@Override
	public boolean placeTile(GridPosition p, List<Tile> tileList) {
		// TODO Auto-generated method stub
		return false;
	}


}


