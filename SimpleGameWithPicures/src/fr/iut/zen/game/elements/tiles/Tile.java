package fr.iut.zen.game.elements.tiles;
import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public interface Tile {
	public Mobs spawn(List<GridPosition> path);
	public void effectOnHero(Hero hero);
	public String getImagePath();
	public GridPosition getPosition();
	//public ArrayList<GridPosition> canBePlacedOn;
	
}
