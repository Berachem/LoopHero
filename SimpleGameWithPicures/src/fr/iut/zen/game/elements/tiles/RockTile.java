package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;

public class RockTile  implements Tile,Serializable {
	
	private final String rockTilePATH = "pictures/RockTile.png";
	private final GridPosition position;
	
	public RockTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	/**
	 * @param lgn 
	 * @param col 
	 * @param placedTiles 
	 * @param r the rock tile that we are checking
	 * @return the number of rock tiles that are adjacent to the Rock tile in parameter (knowing that a Rock tile increase 1% of maxHP )
	 */
	public static int RockAdjacentsTileBonus(ArrayList<Tile> placedTiles, int col, int lgn, RockTile r){
		Objects.requireNonNull(r);
		List<List<Integer>> decal = List.of(List.of(0,1),List.of(1,0), List.of(-1,0), List.of(0,-1));  
		int count =0;
		for (List<Integer> c : decal) {
			GridPosition p = r.getPosition(); 
			if (p.column()+c.get(0)<col && p.line()+c.get(1)<lgn && placedTiles.contains(new RockTile(new GridPosition(p.line()+c.get(1), p.column()+c.get(0))))) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public String getImagePath() {
		
		return rockTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return null;
	}

	@Override
	public void effectOnHero(Hero hero) {
		hero.increaseMaximumHpPercentage(1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rockTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RockTile other = (RockTile) obj;
		return Objects.equals(rockTilePATH, other.rockTilePATH);
	}

	@Override
	public String toString() {
		return "RockTile [rockTilePATH=" + rockTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}
}
