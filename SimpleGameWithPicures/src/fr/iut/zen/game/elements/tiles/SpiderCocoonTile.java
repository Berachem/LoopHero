package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Spider;

public class SpiderCocoonTile implements Tile,Serializable{
	private final String spiderCocoonTilePATH = "pictures/SpiderCocoonTile.png";
	private final GridPosition position;
	
	public SpiderCocoonTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	
	@Override
	public String getImagePath() {
		
		return spiderCocoonTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Spider(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(spiderCocoonTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpiderCocoonTile other = (SpiderCocoonTile) obj;
		return Objects.equals(spiderCocoonTilePATH, other.spiderCocoonTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "SpiderCocoonTile [spiderCocoonTilePATH=" + spiderCocoonTilePATH + ", position=" + position + "]";
	}
}
