package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;


public class Spider extends AbstractMobs {

	private final String SpiderPATH = "pictures/Spider.png";

	
	public Spider(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.45, 8, 3.1);
	}


	@Override
	public String getImagePath() {
		return SpiderPATH;
	}

	
	@Override 
	public String toString() {
		return "Spider " + stats + ", health=" + health ;
	}


}
