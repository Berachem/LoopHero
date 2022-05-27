package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Oblivion implements Card,Serializable {

	private final String obliPATH = "pictures/oblivion.png";
	
	@Override
	public String getImagePath() {
		
		return obliPATH;
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


