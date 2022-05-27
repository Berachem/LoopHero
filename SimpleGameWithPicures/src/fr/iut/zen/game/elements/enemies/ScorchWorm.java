package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;


public class ScorchWorm extends AbstractMobs {

	private final String ScorchWormPATH = "pictures/ScorchWorm.png";

	
	public ScorchWorm(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.85, 10, 2.7);
	}


	@Override
	public String getImagePath() {
		return ScorchWormPATH;
	}

	

	@Override 
	public String toString() {
		return "Scorch Worm " + stats + ", health=" + health ;
	}
}
