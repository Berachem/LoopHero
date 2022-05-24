package fr.iut.zen.game.elements.cards;

import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.BeaconTile;
import fr.iut.zen.game.elements.tiles.CemeteryTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Beacon implements Card{
	
	private final String beaconPATH = "pictures/BeaconCard.png";
	
	@Override
	public String getImagePath() {
		
		return beaconPATH;
	}


	@Override
	public String getType() {
		return "Landscape"; //field????
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new BeaconTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Beacon Card";
	}
}
