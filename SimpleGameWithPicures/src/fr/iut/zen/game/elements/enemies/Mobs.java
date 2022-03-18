package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;

public interface Mobs {

	public int attack();
	public void die();
	public boolean isInPosition(GridPosition p);
	/*
	public List<Equipment> dropEquipment();
	public List<Card> dropCard();
	public int dropMaterials();
	*/
}
