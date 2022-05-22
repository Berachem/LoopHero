package fr.iut.zen.game.elements.tiles;

import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.enemies.Mobs;

public class WheatFieldsTile implements Tile {
	
	private final String wheatFieldsTilePATH = "pictures/WheatFieldsTile.png";
	private final GridPosition position;
	
	public WheatFieldsTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	@Override
	public String getImagePath() {
		
		return wheatFieldsTilePATH;
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
		return "WheatFieldsTile [wheatFieldsTilePATH=" + wheatFieldsTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(wheatFieldsTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WheatFieldsTile other = (WheatFieldsTile) obj;
		return Objects.equals(wheatFieldsTilePATH, other.wheatFieldsTilePATH);
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}
	
	
}

