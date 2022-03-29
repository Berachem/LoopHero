package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public interface Card{
	
	public String getImagePath();
	public String getType();
	public Tile getTile(GridPosition p);
	public boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<GridPosition> emplacements);
	public static List<Card> catalog(){
		return Arrays.asList(new Grove(), new Meadow(), new Rock());
	}
	
}