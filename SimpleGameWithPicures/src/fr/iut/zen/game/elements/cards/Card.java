package fr.iut.zen.game.elements.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public interface Card{
	
	public String getImagePath();
	public String getType();
	public Tile getTile(GridPosition p);
	
	public default boolean placeTile(GridPosition p, ArrayList<Tile> tileList, ArrayList<GridPosition> emplacements) {
		Objects.requireNonNull(p);
		Objects.requireNonNull(tileList);
		Objects.requireNonNull(emplacements);
		if (emplacements.contains(p)) {
			tileList.add(getTile(p));
			emplacements.remove(p);
			return true;
		}
		return false;
	}
	
	public static List<Card> catalog(){
		return Arrays.asList(new Grove(), new Meadow(), new Rock());
	}
	
}