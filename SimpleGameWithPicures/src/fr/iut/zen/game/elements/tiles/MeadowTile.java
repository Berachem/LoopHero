package fr.iut.zen.game.elements.tiles;

import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public class MeadowTile implements Tile {
	
	private final String meadowTilePATH = "pictures/Meadow_tile.png";
	private final GridPosition position;
	
	public MeadowTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	@Override
	public String getImagePath() {
		
		return meadowTilePATH;
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
		return "MeadowTile [meadowTilePATH=" + meadowTilePATH + ", position=" + position + "]";
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
	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	
}
