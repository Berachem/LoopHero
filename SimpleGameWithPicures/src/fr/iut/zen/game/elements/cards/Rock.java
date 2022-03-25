package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Rock implements Card {
	final String rockPATH = "pictures/RockCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return rockPATH;
	}


	@Override
	public boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<Tile> emplacements) {
		// TODO Auto-generated method stub
		return false;
	}


}
