package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;
import fr.iut.zen.game.elements.tiles.VampireMansionTile;

public class VampireMansion implements Card,Serializable{
	private final String vampireMansionPATH = "pictures/VampireMansionCard.png";
	
	@Override
	public String getImagePath() {
		
		return vampireMansionPATH;
	}


	@Override
	public String getType() {
		return "RoadSide";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new VampireMansionTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Vampire Mansion Card";
	}
}
