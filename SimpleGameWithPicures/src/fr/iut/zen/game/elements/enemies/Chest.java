package fr.iut.zen.game.elements.enemies;

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

	
	
}
