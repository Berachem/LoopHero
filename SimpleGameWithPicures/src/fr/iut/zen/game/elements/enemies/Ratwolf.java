package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Ratwolf implements Mobs{

	final String slimePATH = "pictures/green-slime.png";
	private int health;
	private final int damage;
	private final GridPosition locationRatwolf;
	
	public Ratwolf(GridPosition locationRatwolf, int LoopCount) {
		health=16;
		damage=4;
		this.locationRatwolf = locationRatwolf;
	}
	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return damage;
	}
	@Override
	public void attacked(int dmg) {
		// TODO Auto-generated method stub
		health-=dmg;
		
	}
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return health>0;
	}
	@Override
	public boolean isInPosition(GridPosition p) {
		// TODO Auto-generated method stub
		return locationRatwolf.equals(p);
	}
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return slimePATH;
	}
	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return locationRatwolf;
	}
	@Override
	public int dropRessources() {
		// TODO Auto-generated method stub
		return 30;
	}
	@Override
	public List<Card> dropCards() {
		ArrayList<Card> list = new ArrayList<>();
		list.add(new Rock());	
		return list;
	}
	@Override
	public List<Equipment> dropEquipments() {
		ArrayList<Equipment> list = new ArrayList<>();
		
		return list;
	}
	
}
