package fr.iut.zen.game.elements.enemies;

import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

public interface Mobs {

	public String getImagePath();
	public GridPosition getPosition();
	public double attack();
	public void attacked(double dmg);
	public boolean isAlive();
	public boolean isInPosition(GridPosition p);
	public int dropRessources();
	public List<Card> dropCards(); //drops a random card
	public List<Equipment> dropEquipments();
	
	/*
	public List<Equipment> dropEquipment();
	public int dropMaterials();
	*/
}
