package fr.iut.zen.game.elements.cards;

import java.util.Objects;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card {

	private final String grovePATH = "pictures/oblivion.png";
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
	}


	@Override
	public String getType() {
		return "Oblivion";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return null;
	}
	
	@Override
	
	public String toString() {
		return "Oblivion Card";
	}
	
	


}


