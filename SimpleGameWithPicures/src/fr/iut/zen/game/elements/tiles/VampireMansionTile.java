package fr.iut.zen.game.elements.tiles;

import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Vampire;

public class VampireMansionTile implements Tile{
	private final String vampireMansionTilePATH = "pictures/VampireMansionTile.png";
	private final GridPosition position;
	
	public VampireMansionTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	
	@Override
	public String getImagePath() {
		
		return vampireMansionTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Vampire(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(vampireMansionTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VampireMansionTile other = (VampireMansionTile) obj;
		return Objects.equals(vampireMansionTilePATH, other.vampireMansionTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "VampireMansionTile [vampireMansionTilePATH=" + vampireMansionTilePATH + ", position=" + position + "]";
	}

}
