package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Ratwolf implements Mobs {

	private final String RatwolfPATH = "pictures/newRatWolf.png";
	private double health;
	private final double damage;
	private final GridPosition locationRatwolf;
	private final double DropCardChance = 0.60;
	// le reste jte laisse g�rer stp ^^
	
	public Ratwolf(GridPosition location, int LoopCount) {
		Objects.requireNonNull(location);
		health=16;
		damage=3.6;
		this.locationRatwolf = location;
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
	public double attack() {
		// TODO Auto-generated method stub
		return damage;
	}

	@Override
	public void attacked(double dmg) {
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
		Objects.requireNonNull(p);
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
		double random = new Random().nextDouble(1.0);
		ArrayList<Card> dropCardList = new ArrayList<>(); 
		if (random<DropCardChance) {
			
			dropCardList.add(Card.catalog().get(new Random().nextInt(Card.catalog().size())));
			
		}
		return dropCardList;
	}

	@Override
	public List<Equipment> dropEquipments() {
		// TODO Auto-generated method stub
		return null;
	}

}
