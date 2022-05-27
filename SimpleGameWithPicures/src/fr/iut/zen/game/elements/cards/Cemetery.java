package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.CemeteryTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Cemetery implements Card,Serializable {

	private final String cemeteryPATH = "pictures/CemeteryCard.png";
	
	@Override
	public String getImagePath() {
		
		return cemeteryPATH;
	}


	@Override
	public String getType() {
		return "Road";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new CemeteryTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Cemetery Card";
	}
	
	


}