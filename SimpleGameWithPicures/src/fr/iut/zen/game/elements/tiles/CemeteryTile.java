package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Skeleton;

public class CemeteryTile implements Tile,Serializable {
	
	private final String cemeteryTilePATH = "pictures/CemeteryTile.png";
	private final GridPosition position;
	
	public CemeteryTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	
	@Override
	public String getImagePath() {
		
		return cemeteryTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Skeleton(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(cemeteryTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CemeteryTile other = (CemeteryTile) obj;
		return Objects.equals(cemeteryTilePATH, other.cemeteryTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "CemeteryTile [cemeteryTilePATH=" + cemeteryTilePATH + ", position=" + position + "]";
	}
	
	
	

}
