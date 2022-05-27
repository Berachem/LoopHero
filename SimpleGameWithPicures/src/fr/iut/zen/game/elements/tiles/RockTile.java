package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;

public class RockTile  implements Tile,Serializable {
	
	private final String rockTilePATH = "pictures/RockTile.png";
	private final GridPosition position;
	
	public RockTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	@Override
	public String getImagePath() {
		
		return rockTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return null;
	}

	@Override
	public void effectOnHero(Hero hero) {
		hero.increaseMaximumHpPercentage(1);
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

	@Override
	public String toString() {
		return "RockTile [rockTilePATH=" + rockTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}
}
