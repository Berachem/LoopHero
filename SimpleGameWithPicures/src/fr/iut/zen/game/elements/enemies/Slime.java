package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
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
	private int LastCounterAttackDamage;
	
	
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
		System.out.println("le random : "+random);
		ArrayList<Equipment> dropEquipmentList = new ArrayList<>(); 
		if (random<DropEquipmentChance) {
			int indexEquipmentPicked = new Random().nextInt(Equipment.catalog(loopCount).size());
			dropEquipmentList.add(Equipment.catalog(loopCount).get(indexEquipmentPicked));
			System.out.println(loopCount);
			System.out.println(Equipment.catalog(loopCount).get(indexEquipmentPicked));
			
		}
		return dropEquipmentList;
	}

	@Override
	public double getHp() {
		return health;
	}
	
	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return "Slime " + stats + ", health=" + health + "]";
	}

	@Override
	public int attacked(Hero hero) {
		double random = new Random().nextDouble(100);
		if (random > stats.getEvade()) { // Il esquive pas...
			if (health-hero.attack() + stats.getDefense()<=0) {
				health=0; // il est mort après l'attaque
			}else {
				health-=hero.attack()+stats.getDefense();
				LastCounterAttackDamage = 0;
				counterAttack(hero);	
			}
			
			return 1; // il a pris l'attaque et a contre attaqué (ou pas)
		}
		return 0; // il a equivé l'attaque
		
	}
	
	public void counterAttack(Hero h) {
		double random = new Random().nextDouble(100);
		if (random<stats.getCounter()) { // Il contre attque
			h.counterAttacked(6);
			LastCounterAttackDamage = 6;
		}
	}

	@Override
	public void counterAttacked(int dmg) {
		if (health-dmg<=0) {
			health = 0;
		}else {
			health-=dmg;
		}
		
	}

	@Override
	public double getLastCounterAttackDamage() {
		return LastCounterAttackDamage;
	}
	
	

	
}
