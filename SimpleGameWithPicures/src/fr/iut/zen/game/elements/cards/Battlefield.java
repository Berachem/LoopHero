package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.BattlefieldTile;
import fr.iut.zen.game.elements.tiles.CemeteryTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Battlefield implements Card,Serializable {

	private final String battlefieldPATH = "pictures/BattlefieldCard.png";
	
	@Override
	public String getImagePath() {
		
		return battlefieldPATH;
	}


	@Override
	public String getType() {
		return "RoadSide";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new BattlefieldTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Battlefield Card";
	}
}
