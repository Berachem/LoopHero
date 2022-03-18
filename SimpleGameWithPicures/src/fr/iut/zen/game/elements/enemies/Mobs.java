package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;

public interface Mobs {

	public String getImagePath();
	public GridPosition getPosition();
	public int attack();
	public void attacked(int dmg);
	public boolean isAlive();
	public boolean isInPosition(GridPosition p);
	public int dropRessources();
	/*
	public List<Equipment> dropEquipment();
	public List<Card> dropCard();
	public int dropMaterials();
	*/
}
