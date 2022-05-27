package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;

import fr.iut.zen.game.GridPosition;

public class Scarecrow extends AbstractMobs{

	private final String ScarecrowPATH = "pictures/Scarecrow.png";

	
	public Scarecrow(GridPosition location, int LoopCount) { //double DropEquipmentChance, double baseHealth , double baseDamage
		super(location, LoopCount, 1.00, 18, 8.25);
	}


	@Override
	public String getImagePath() {
		return ScarecrowPATH;
	}

	
	@Override 
	public String toString() {
		return "Scarecrow: " + stats + ", health=" + health ;
	}
	
	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Stable Branches");
		list.add("Ration");
		list.add("Craft Fragment");
		return list;
	}


	
	
}
