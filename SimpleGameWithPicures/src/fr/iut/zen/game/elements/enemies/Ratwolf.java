package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Shield;
import fr.iut.zen.game.elements.equipments.Weapon;

public class Ratwolf implements Mobs {

	private final String RatwolfPATH = "pictures/RatWolf.png";
	private final GridPosition locationRatwolf;
	private final double DropCardChance = 0.60;
	private final double DropEquipmentChance = 0.40;
	private final double baseHealth = 16;
	private final double baseDamage = 3.6;
	private double health;
	private Stats stats;

	
	public Ratwolf(GridPosition location, int LoopCount) {
		Objects.requireNonNull(location);
		
		//base × lvl × 0.95 × (1 + (lvl − 1) × 0.02),
		health=baseHealth*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		double damage = baseDamage*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		this.locationRatwolf = location;
		stats = new Stats(damage, 0, 0, 0, 0, 0, 0);
		
		//double damage, double defense, double maximumHP, double counter, 
		//double vampirism, double regen, double evade
	}
	
	@Override
	public String getImagePath() {
		return RatwolfPATH;
	}

	@Override
	public GridPosition getPosition() {
		return locationRatwolf;
	}

	@Override
	public double attack() {
		return stats.getDamage();
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
		return locationRatwolf.equals(p);
	}

	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Living Fabric");
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
	public List<Equipment> dropEquipments(int loopCount) {
		double random = new Random().nextDouble(1.0);
		ArrayList<Equipment> dropEquipmentList = new ArrayList<>(); 
		if (random<DropEquipmentChance) {
			
			dropEquipmentList.add(Equipment.catalog().get(new Random().nextInt(Equipment.catalog().size())));
			
		}
		return dropEquipmentList;
	}

	@Override
	public double getHp() {
		// TODO Auto-generated method stub
		return health;
	}

}
