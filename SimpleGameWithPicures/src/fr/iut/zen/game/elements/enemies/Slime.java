package fr.iut.zen.game.elements.enemies;

import java.io.Serializable;
import java.util.ArrayList;

import fr.iut.zen.game.GridPosition;

public class Slime extends AbstractMobs implements Serializable {
	
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
		return "Slime " + stats + ", health=" + health + ", pos : "+super.getPosition();
	}
	
	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Craft Fragment");
		list.add("Shapeless Mass");
		return list;
	}
	
	@Override
	public boolean hasSoul() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
