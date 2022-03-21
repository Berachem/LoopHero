package fr.iut.zen.game.elements.tiles;

import java.util.Objects;

import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public class MeadowTile implements Tile {
	
	final String meadowTilePATH = "pictures/Meadow_tile.png";
	
	@Override
	public String getImagePath() {
		
		return meadowTilePATH;
	}

	@Override
	public Mobs spawn() {
		
		return null;
	}

	@Override
	public void effectOnHero(Hero hero) {
		
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(meadowTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeadowTile other = (MeadowTile) obj;
		return Objects.equals(meadowTilePATH, other.meadowTilePATH);
	}
	
	
}
