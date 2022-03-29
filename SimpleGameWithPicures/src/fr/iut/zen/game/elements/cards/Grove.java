package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card {

	private final String grovePATH = "pictures/GroveCard.png";
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
	}


	@Override
	public boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<GridPosition> emplacements) {
		Objects.requireNonNull(p);
		Objects.requireNonNull(tileList);
		Objects.requireNonNull(emplacements);
		
		
		if (emplacements.contains(p)) {
			tileList.add(getTile(p));
			emplacements.remove(p);
			return true;
		}
		
		return false;
	}



	@Override
	public String getType() {
		return "Road";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new GroveTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Grove Card";
	}
	
	


}


