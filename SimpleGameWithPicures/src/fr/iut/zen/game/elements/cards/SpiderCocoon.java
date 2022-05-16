package fr.iut.zen.game.elements.cards;

import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.SpiderCocoonTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class SpiderCocoon implements Card{
	private final String spiderCocoonPATH = "pictures/SpiderCocoonCard.png";
	
	@Override
	public String getImagePath() {
		
		return spiderCocoonPATH;
	}


	@Override
	public String getType() {
		return "RoadSide";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new SpiderCocoonTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Spider Cocoon Card";
	}
}
