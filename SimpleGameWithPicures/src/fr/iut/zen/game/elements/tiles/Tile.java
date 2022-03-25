package fr.iut.zen.game.elements.tiles;
import java.util.ArrayList;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public interface Tile {
	public Mobs spawn();
	public void effectOnHero(Hero hero);
	public String getImagePath();
	//public ArrayList<GridPosition> canBePlacedOn;
	
}
