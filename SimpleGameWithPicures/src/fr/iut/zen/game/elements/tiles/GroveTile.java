package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;

public class GroveTile  implements Tile,Serializable {
	
	private final String groveTilePATH = "pictures/GroveTile.png";
	private final GridPosition position;
	
	public GroveTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	/**
	 * Spawns a RatWolf at a random location adjacent of the Grove tile or on The groveTile
	 * @param path, int LoopCount 
	 * @param mobsOnthePath 
	 * @param placedTiles 
	 */
	public static void spawnRatwolf(ArrayList<Tile> placedTiles, List<Mobs> MobsOnthePath, ArrayList<GridPosition> path, int LoopCount) {
		for (Tile t : placedTiles) {
			if ( t instanceof GroveTile) {
				int tilePositionInPath = path.indexOf(t.getPosition());
				List<Integer> PotentialIndex = Arrays.asList(tilePositionInPath-1, tilePositionInPath,tilePositionInPath+1); 
				int randomPosition = new Random().nextInt(3);
				while (randomPosition==0) {
					randomPosition = new Random().nextInt(3);
				}
				MobsOnthePath.add(new Ratwolf(path.get(PotentialIndex.get(randomPosition)), LoopCount));
			}
		}
	}
	
	
	@Override
	public String getImagePath() {
		
		return groveTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Ratwolf(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(groveTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroveTile other = (GroveTile) obj;
		return Objects.equals(groveTilePATH, other.groveTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public String toString() {
		return "GroveTile [groveTilePATH=" + groveTilePATH + ", position=" + position + "]";
	}

	@Override
	public String getResource() {
		return "Stable Branches";
	}
	
	
	

}
