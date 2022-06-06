package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;

import fr.iut.zen.game.GridPosition;

public class Ghost extends AbstractMobs{

	private final String ChestPATH = "pictures/Ghost.png";

	
	public Ghost(GridPosition location, int LoopCount) { //double DropEquipmentChance, double baseHealth , double baseDamage
		super(location, LoopCount, 0, 3, 3);
	}


	@Override
	public String getImagePath() {
		return ChestPATH;
	}

	
	@Override 
	public String toString() {
		return "Ghost: " + stats + ", health=" + health ;
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
		return false;
	}


	
	
}
