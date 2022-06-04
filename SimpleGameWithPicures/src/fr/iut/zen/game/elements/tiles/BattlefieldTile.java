package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Chest;
import fr.iut.zen.game.elements.enemies.Ghost;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Skeleton;

public class BattlefieldTile implements Tile,Serializable {
	
	private final String battlefieldTilePATH = "pictures/BattlefieldTile.png";
	private final GridPosition position;
	
	public BattlefieldTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	public static void spawnChests(ArrayList<Tile> placedTiles, List<Mobs> MobsOnthePath, int LoopCount, ArrayList<GridPosition> path) {
		for (Tile t : placedTiles) {
			if ( t instanceof BattlefieldTile) {
				MobsOnthePath.add(new Chest(path.get(new Random().nextInt(path.size())),LoopCount));
			}
		}
		
	}
	
	/**
	 * effect of the battlefield tile: if an enemy died near the tile, he becomes a ghost
	 * @param pos the position where the mob died
	 * @param m the monster type
	 * @param placedTiles 
	 * @param path 
	 * @param LoopCount 
	 * @param MobsOnthePath 
	 */
	public static void ghostTransformation(GridPosition pos, Mobs m, List<Mobs> MobsOnthePath, int LoopCount, ArrayList<GridPosition> path, ArrayList<Tile> placedTiles) {
		if (!(m instanceof Ghost)) {
			
		
		int ligne = pos.line();
		int col = pos.column();
		Tile previous =  getTileOnGridPosition(new GridPosition(ligne, col-1), placedTiles);
		Tile bottom =  getTileOnGridPosition(new GridPosition(ligne+1, col), placedTiles);
		Tile top =  getTileOnGridPosition(new GridPosition(ligne-1, col), placedTiles);
		Tile next =  getTileOnGridPosition(new GridPosition(ligne, col+1), placedTiles);
		if (previous instanceof BattlefieldTile || bottom instanceof BattlefieldTile  || next instanceof BattlefieldTile || top instanceof BattlefieldTile) {
			MobsOnthePath.add(new Ghost(path.get(path.indexOf(pos)-1), LoopCount));
		}}

	}
	
	private static Tile getTileOnGridPosition(GridPosition pos, ArrayList<Tile> placedTiles) {
		for (Tile t : placedTiles) {
			if (t.getPosition().equals(pos)) {
				return t;
			}
		}
		return null;
	}

	
	
	@Override
	public String getImagePath() {
		
		return battlefieldTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Chest(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(battlefieldTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BattlefieldTile other = (BattlefieldTile) obj;
		return Objects.equals(battlefieldTilePATH, other.battlefieldTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "BattlefieldTile [battlefieldTilePATH=" + battlefieldTilePATH + ", position=" + position + "]";
	}

	@Override
	public String getResource() {
		return null;
	}
	
}
