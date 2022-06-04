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
import fr.iut.zen.game.elements.enemies.Vampire;

public class VampireMansionTile implements Tile,Serializable{
	private final String vampireMansionTilePATH = "pictures/VampireMansionTile.png";
	private final GridPosition position;
	
	public VampireMansionTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	/**
	 * Spawns Vampire based on the nearest road Position next to Vampire Mansion
	 * @param path 
	 * @param loopCount 
	 * @param placedTiles 
	 * @param mobsOnthePath 
	 * @param vampireMansionTile 
	 */
	public static void spawnVampire(VampireMansionTile vampireMansionTile, List<Mobs> MobsOnthePath, ArrayList<Tile> placedTiles, int LoopCount, ArrayList<GridPosition> path) {


				int  tileProjectionPositionInPath = -1;
				int ligne = vampireMansionTile.getPosition().line();
				int col = vampireMansionTile.getPosition().column();
				for (int i = ligne; i<ligne+2;i++) {
					for (int j = col-1; j<col+2;j++){
						
							if (path.contains(new GridPosition(i,j)) && !path.get(0).equals(new GridPosition(i,j))){
								
								tileProjectionPositionInPath =path.indexOf(new GridPosition(i,j));
		
								break;
							}
					}
					if (tileProjectionPositionInPath != -1) {
						break;
					}
					
				}
				if (tileProjectionPositionInPath != -1) {
					
					List<Integer> PotentialIndex = Arrays.asList(tileProjectionPositionInPath-1, tileProjectionPositionInPath,tileProjectionPositionInPath+1); 
					int randomPosition = new Random().nextInt(3);
					while (randomPosition==0) {
						randomPosition = new Random().nextInt(3);
					}
					MobsOnthePath.add(new Vampire(path.get(PotentialIndex.get(randomPosition)), LoopCount));
				System.out.println(path.get(PotentialIndex.get(randomPosition)));
				}
	}
	
	
	@Override
	public String getImagePath() {
		
		return vampireMansionTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Vampire(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(vampireMansionTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VampireMansionTile other = (VampireMansionTile) obj;
		return Objects.equals(vampireMansionTilePATH, other.vampireMansionTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "VampireMansionTile [vampireMansionTilePATH=" + vampireMansionTilePATH + ", position=" + position + "]";
	}

	@Override
	public String getResource() {
		return null;
	}

}
