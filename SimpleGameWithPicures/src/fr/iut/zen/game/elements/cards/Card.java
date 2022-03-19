package fr.iut.zen.game.elements.cards;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public interface Card{
   //String name = "Carte Inconnue";
   //String description = "blablablablablablabla";
	
	public String getImagePath();
	public Tile getTile();
	
}