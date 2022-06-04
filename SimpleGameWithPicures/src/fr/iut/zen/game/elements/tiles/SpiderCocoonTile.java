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
import fr.iut.zen.game.elements.enemies.Spider;

public class SpiderCocoonTile implements Tile,Serializable{
	private final String spiderCocoonTilePATH = "pictures/SpiderCocoonTile.png";
	private final GridPosition position;
	
	public SpiderCocoonTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	public static void spawnSpiderCocoon(List<Mobs> MobsOnthePath, int LoopCount, ArrayList<GridPosition> path, ArrayList<Tile> placedTiles) {
		for (Tile t : placedTiles) {

			if ( t instanceof SpiderCocoonTile) {

				int  tileProjectionPositionInPath = -1;
				int ligne = t.getPosition().line();
				int col = t.getPosition().column();
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
					MobsOnthePath.add(new Spider(path.get(PotentialIndex.get(randomPosition)), LoopCount));
				}

				
				

			}
		}
		
	}
	
	
	@Override
	public String getImagePath() {
		
		return spiderCocoonTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new Spider(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(spiderCocoonTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpiderCocoonTile other = (SpiderCocoonTile) obj;
		return Objects.equals(spiderCocoonTilePATH, other.spiderCocoonTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "SpiderCocoonTile [spiderCocoonTilePATH=" + spiderCocoonTilePATH + ", position=" + position + "]";
	}

	@Override
	public String getResource() {
		return null;
	}
}
