package fr.iut.zen.game.elements.enemies;

import java.util.List;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Ratwolf implements Mobs {

	final String RatwolfPATH = "pictures/ratwolf.png";
	private int health;
	private final int damage;
	private final GridPosition locationRatwolf;
	// le reste jte laisse gérer stp ^^
	
	public Ratwolf(GridPosition locationSlime, int LoopCount) {
		health=16;
		damage=4;//3.6 float
		this.locationRatwolf = locationSlime;
	}
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return RatwolfPATH;
	}

	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return locationRatwolf;
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
	public int dropRessources() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public List<Card> dropCards() {
		// TODO Auto-generated method stub
		return List.of(new Meadow());
	}

	@Override
	public List<Equipment> dropEquipments() {
		// TODO Auto-generated method stub
		return null;
	}

}
