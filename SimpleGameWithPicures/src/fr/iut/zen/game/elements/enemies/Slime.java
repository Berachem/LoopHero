package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Slime implements Mobs {
	
	private final String slimePATH = "pictures/pinkSlime.png";
	private double health;
	private final double damage;
	private final GridPosition locationSlime;
	private final double DropCardChance = 0.65;
	// le reste jte laisse g�rer stp ^^
	
	public Slime(GridPosition locationSlime, int LoopCount) {
		health=13;
		damage=3;
		this.locationSlime = locationSlime;
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
		// TODO Auto-generated method stub
		return locationSlime.equals(p);
	}
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return slimePATH;
	}
	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return locationSlime;
	}
	@Override
	public int dropRessources() {
		// TODO Auto-generated method stub
		return 30;
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
		ArrayList<Equipment> list = new ArrayList<>();
		
		return list;
	}


}
