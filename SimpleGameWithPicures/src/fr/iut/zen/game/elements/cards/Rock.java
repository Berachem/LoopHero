package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.RockTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Rock implements Card {
	private final String rockPATH = "pictures/RockCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return rockPATH;
	}


	@Override
	public boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<GridPosition> emplacements) {
		if (emplacements.contains(p)) {
			tileList.add(getTile(p));
			emplacements.remove(p);
			return true;
		}
		return false;
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Landscape";
	}


	@Override
	public Tile getTile(GridPosition p) {
		// TODO Auto-generated method stub
		return new RockTile(p);
	}


}
