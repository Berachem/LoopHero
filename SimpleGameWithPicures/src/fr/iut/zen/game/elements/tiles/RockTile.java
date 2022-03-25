package fr.iut.zen.game.elements.tiles;

import java.util.Objects;

import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;

public class RockTile  implements Tile {
	
	final String rockTilePATH = "pictures/Rock_tile.png";
	
	@Override
	public String getImagePath() {
		
		return rockTilePATH;
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
		return Objects.hash(rockTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RockTile other = (RockTile) obj;
		return Objects.equals(rockTilePATH, other.rockTilePATH);
	}
}
