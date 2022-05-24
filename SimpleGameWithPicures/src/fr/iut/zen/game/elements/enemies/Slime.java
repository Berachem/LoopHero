package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;

public class Slime extends AbstractMobs {
	
	private final String slimePATH = "pictures/pinkSlime.png";
	
	public Slime(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.35, 13, 3.3);
	}
	
	
	@Override
	public String getImagePath() {
		return slimePATH;
	}
	
	@Override
	public String toString() {
		return "Slime " + stats + ", health=" + health + ", pos : "+super.getPosition()+ "]";
	}
	
}
