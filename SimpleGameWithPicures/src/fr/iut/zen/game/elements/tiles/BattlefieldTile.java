package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Chest;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Skeleton;

public class BattlefieldTile implements Tile,Serializable {
	
	private final String battlefieldTilePATH = "pictures/BattlefieldTile.png";
	private final GridPosition position;
	
	public BattlefieldTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	
	@Override
	public String getImagePath() {
		
		return battlefieldTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Chest(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(battlefieldTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BattlefieldTile other = (BattlefieldTile) obj;
		return Objects.equals(battlefieldTilePATH, other.battlefieldTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "BattlefieldTile [battlefieldTilePATH=" + battlefieldTilePATH + ", position=" + position + "]";
	}
	
}
