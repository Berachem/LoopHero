package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public class BeaconTile implements Tile,Serializable{
	
	private final String beaconTilePATH = "pictures/BeaconTile.png";
	private final GridPosition position;
	
	public BeaconTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	@Override
	public String getImagePath() {
		
		return beaconTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return null;
	}

	@Override
	public void effectOnHero(Hero hero) {
		Objects.requireNonNull(hero);
		
	}
	

	@Override
	public String toString() {
		return "BeaconTile [beaconTilePATH=" + beaconTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(beaconTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeaconTile other = (BeaconTile) obj;
		return Objects.equals(beaconTilePATH, other.beaconTilePATH);
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}

	@Override
	public String getResource() {
		return null;
	}
}
