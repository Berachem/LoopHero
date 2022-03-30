package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	
	
	public Slime(GridPosition locationSlime, int LoopCount) {
		Objects.requireNonNull(locationSlime);
		health=13;
		damage=6;
		this.locationSlime = locationSlime;
	}
	
	@Override
	public double attack() {
		return damage;
	}
	
	@Override
	public void attacked(double dmg) {
		health-=dmg;
	}
	
	@Override
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public boolean isInPosition(GridPosition p) {
		Objects.requireNonNull(p);
		return locationSlime.equals(p);
	}
	
	@Override
	public String getImagePath() {
		return slimePATH;
	}
	@Override
	public GridPosition getPosition() {
		return locationSlime;
	}
	
	
	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Craft Fragment");
		list.add("Shapeless Mass");
		return list;
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
