package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Meadow implements Card {
	final String meadowPATH = "pictures/MeadowCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return meadowPATH;
	}


	@Override
	public boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<Tile> emplacements) {
		// TODO Auto-generated method stub
		return false;
	}


}
