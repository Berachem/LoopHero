package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;

import fr.iut.zen.game.GridPosition;

public class Chest extends AbstractMobs{

	private final String ChestPATH = "pictures/Chest.png";

	
	public Chest(GridPosition location, int LoopCount) { //double DropEquipmentChance, double baseHealth , double baseDamage
		super(location, LoopCount, 1.00, 11, 0.6);
	}


	@Override
	public String getImagePath() {
		return ChestPATH;
	}

	
	@Override 
	public String toString() {
		return "Chest: " + stats + ", health=" + health ;
	}
	
	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Craft Fragment");
		list.add("Stable Branches");
		return list;
	}


	@Override
	public boolean hasSoul() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
