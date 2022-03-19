package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card {
	final String grovePATH = "pictures/GroveCard.png";
	
	private final GridPosition location;
	
	
	public Grove(GridPosition location) {
		
		this.location = location;
	}
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
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
		
		return null;
	}
}
