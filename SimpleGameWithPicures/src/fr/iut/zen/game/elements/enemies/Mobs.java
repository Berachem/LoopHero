package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

public interface Mobs {

	public String getImagePath();
	public GridPosition getPosition();
	public double attack();
	public int attacked(Hero hero);
	public void counterAttacked(int dmg);
	public double getLastCounterAttackDamage();
	public void counterAttack(Hero h);
	public boolean isAlive();
	public boolean isInPosition(GridPosition p);
	public ArrayList<String> dropRessources();
	public List<Card> dropCards(); //drops a random card
	public List<Equipment> dropEquipments(int loopCount);
	public double getHp();
	public Stats getStats();
	
	
	
	/*
	public List<Equipment> dropEquipment();
	public int dropMaterials();
	*/
	
	
}
