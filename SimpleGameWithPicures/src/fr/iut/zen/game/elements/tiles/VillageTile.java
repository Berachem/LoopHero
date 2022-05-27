package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public class VillageTile implements Tile,Serializable {
	
	private final String villageTilePATH = "pictures/VillageTile.png";
	private final GridPosition position;
	
	public VillageTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	@Override
	public String getImagePath() {
		
		return villageTilePATH;
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
		return "VillageTile [villageTilePATH=" + villageTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(villageTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VillageTile other = (VillageTile) obj;
		return Objects.equals(villageTilePATH, other.villageTilePATH);
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}
	
	
}

