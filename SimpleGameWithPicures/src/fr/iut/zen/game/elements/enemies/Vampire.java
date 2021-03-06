package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;

import fr.iut.zen.game.GridPosition;


public class Vampire extends AbstractMobs {

	private final String VampirePATH = "pictures/Vampire.png";
	

	
	public Vampire(GridPosition location, int LoopCount) {
		super(location, LoopCount, 0.45, 18, 5.8);
	}
	
	public double attack() {
		super.health+= 0.02*super.attack();
		return super.attack();
		
	}

	@Override
	public String getImagePath() {
		return VampirePATH;
	}

	

	@Override 
	public String toString() {
		return "Vampire " + stats + ", health=" + health ;
	}

	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Pitiful Remain");
		return list;
	}
	
	@Override
	public boolean hasSoul() {
		// TODO Auto-generated method stub
		return true;
	}
}
