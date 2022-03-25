package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
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
	public void placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<Tile> emplacements) {
		
		tileList.add(null);
	}



	@Override
	public String getType() {
		return "Road";
	}
	
	


}


