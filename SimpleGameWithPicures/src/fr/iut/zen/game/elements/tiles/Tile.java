package fr.iut.zen.game.elements.tiles;

import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public interface Tile {
	public Mobs spawn();
	public void effectOnHero(Hero hero);
	public String getImagePath();
	
}
