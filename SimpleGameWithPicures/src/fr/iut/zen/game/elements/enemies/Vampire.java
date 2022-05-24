package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;


public class Vampire extends AbstractMobs {

	private final String VampirePATH = "pictures/Vampire.png";
	

	
	public Vampire(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.45, 18, 5.8);
	}


	@Override
	public String getImagePath() {
		return VampirePATH;
	}

	

	@Override 
	public String toString() {
		return "Vampire " + stats + ", health=" + health + "]";
	}


}
