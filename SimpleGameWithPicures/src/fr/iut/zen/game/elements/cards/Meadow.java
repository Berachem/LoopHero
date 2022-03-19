package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Meadow implements Card {
	final String meadowPATH = "pictures/MeadowCard.png";
	
	private final GridPosition location;
	
	
	public Meadow(GridPosition location) {
		
		this.location = location;
	}
	
	@Override
	public String getImagePath() {
		
		return meadowPATH;
	}
	
	@Override
	public GridPosition getPosition() {
		return location;
	}
	
	@Override
	public boolean isInPosition(GridPosition p) {
		
		return location.equals(p);
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return null;
	}
}
