package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.MeadowTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Meadow implements Card {
	final String meadowPATH = "pictures/MeadowCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return meadowPATH;
	}


	@Override
	public void placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<GridPosition> emplacements) {
		if (emplacements.contains(p)) {
			tileList.add(getTile(p));
			emplacements.remove(p);
		}
	}


	@Override
	public String getType() {

		return "Landscape";
	}


	@Override
	public Tile getTile(GridPosition p) {
		// TODO Auto-generated method stub
		return new MeadowTile(p);
	}


}
