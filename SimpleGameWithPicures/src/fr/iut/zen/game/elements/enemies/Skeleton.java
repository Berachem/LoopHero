package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;

public class Skeleton extends AbstractMobs {

	private final String SkeletonPATH = "pictures/Skeleton.png";

	
	public Skeleton(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.85, 12, 9);
	}


	@Override
	public String getImagePath() {
		return SkeletonPATH;
	}

	
	@Override 
	public String toString() {
		return "Skeleton " + stats + ", health=" + health + "]";
	}



	public static void spawnSkeletonCimetery(List<Mobs> mobsOnthePath, ArrayList<Tile> cimeteryTiles, int loopCount ) {
		for (Tile t : cimeteryTiles) {
			mobsOnthePath.add(new Skeleton(t.getPosition(), loopCount));
		}
		
	}
}
