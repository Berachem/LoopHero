package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Weapon;

public class Slime implements Mobs {
	
	private final String slimePATH = "pictures/pinkSlime.png";
	private final GridPosition locationSlime;
	private final double DropCardChance = 0.65;
	private final double DropEquipmentChance = 0.35;
	private final double baseHealth = 13;
	private final double baseDamage = 3.3;
	private Stats stats;
	private double health;
	
	
	public Slime(GridPosition locationSlime, int LoopCount) {
		Objects.requireNonNull(locationSlime);
		health=baseHealth*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		double damage = baseDamage*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		stats = new Stats(damage, 0, 0, 0, 0, 0, 0);
		//double damage, double defense, double maximumHP, double counter, 
		//double vampirism, double regen, double evade
	
		this.locationSlime = locationSlime;
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
