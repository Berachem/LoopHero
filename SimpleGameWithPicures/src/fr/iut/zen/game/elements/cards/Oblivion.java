package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Oblivion implements Card {

	private final String obliPATH = "pictures/GroveCard.png";
	
	@Override
	public String getImagePath() {
		
		return obliPATH;
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


