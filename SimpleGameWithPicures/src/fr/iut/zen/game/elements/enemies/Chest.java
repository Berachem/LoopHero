package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

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
		return "Chest: " + stats + ", health=" + health + "]";
	}

	
	
}
