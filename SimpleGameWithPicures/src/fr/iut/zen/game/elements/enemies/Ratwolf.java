package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;

public class Ratwolf extends AbstractMobs {

	private final String RatwolfPATH = "pictures/RatWolf.png";
	

	
	public Ratwolf(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.4, 16, 3.6);
	}
	
	@Override
	public String getImagePath() {
		return RatwolfPATH;
	}


	@Override 
	public String toString() {
		return "Ratwolf " + stats + ", health=" + health ;
	}
}
