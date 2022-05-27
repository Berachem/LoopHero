package fr.iut.zen.game.elements.enemies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

abstract class AbstractMobs implements Mobs, Serializable{
	
	private final GridPosition location;
	private final double DropCardChance ;
	private final double DropEquipmentChance;
	private final double baseHealth ;
	private final double baseDamage ;
	protected double health;//TEMPORAIRE
	protected Stats stats;//TEMPORAIRE
	private int LastCounterAttackDamage;

	public AbstractMobs(GridPosition location, int LoopCount, double DropEquipmentChance, double baseHealth , double baseDamage) {
		
		this.location =Objects.requireNonNull(location);
		this.DropEquipmentChance = DropEquipmentChance;
		this.DropCardChance = 1.0 - DropEquipmentChance;
		this.baseHealth = baseHealth;
		this.baseDamage = baseDamage;
		
		
		//base × lvl × 0.95 × (1 + (lvl − 1) × 0.02),
		health=baseHealth*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		double damage = baseDamage*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		stats = new Stats(damage, 0, 0, 0, 0, 0, 5);
		
		//double damage, double defense, double maximumHP, double counter, 
		//double vampirism, double regen, double evade
		
	}
	
	@Override
	public GridPosition getPosition() {
		return location;
	}

	@Override
	public double attack() {
		return stats.getDamage();
	}

	public void counterAttacked(int dmg) {
		if (health-dmg <=0) { // he strikes back
			health = 0;
		}else {
			health-=dmg;
		}
	}
	@Override
	public int attacked(Hero hero) {
		double random = new Random().nextDouble(100);
		if (random>stats.getEvade()) { //It didn't dodge...
			if (health-hero.attack() + stats.getDefense()<=0) {
				health=0; // he died after being attacked
			}else {
				health-=hero.attack()+stats.getDefense();
				LastCounterAttackDamage = 0;
				counterAttack(hero);	
			}
			
			return 1; // he took damage and attacked back
		}
		return 0; // he dodged
		
	}
	
	public void counterAttack(Hero h) {
		double random = new Random().nextDouble(100);
		if (random<stats.getCounter()) { // he strikes back
			h.counterAttacked(6);
			LastCounterAttackDamage = 6;
		}
	}
	
	
	
	
	@Override
	public boolean isAlive() {
		return health>0;
	}

	@Override
	public boolean isInPosition(GridPosition p) {
		Objects.requireNonNull(p);
		return location.equals(p);
	}

	@Override
	public ArrayList<String> dropRessources() {
		ArrayList<String> list = new ArrayList<>();
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
			int indexEquipmentPicked = new Random().nextInt(Equipment.catalog(loopCount).size());
			dropEquipmentList.add(Equipment.catalog(loopCount).get(indexEquipmentPicked));
			
		}
		return dropEquipmentList;
	}

	@Override
	public double getHp() {
		return health;
	}
 
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(DropCardChance, DropEquipmentChance, LastCounterAttackDamage, baseDamage, baseHealth,
				health, location, stats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMobs other = (AbstractMobs) obj;
		return Double.doubleToLongBits(DropCardChance) == Double.doubleToLongBits(other.DropCardChance)
				&& Double.doubleToLongBits(DropEquipmentChance) == Double.doubleToLongBits(other.DropEquipmentChance)
				&& LastCounterAttackDamage == other.LastCounterAttackDamage
				&& Double.doubleToLongBits(baseDamage) == Double.doubleToLongBits(other.baseDamage)
				&& Double.doubleToLongBits(baseHealth) == Double.doubleToLongBits(other.baseHealth)
				&& Double.doubleToLongBits(health) == Double.doubleToLongBits(other.health)
				&& Objects.equals(location, other.location) && Objects.equals(stats, other.stats);
	}

	public Stats getStats() {
		return stats;
	}

	
	@Override
	public double getLastCounterAttackDamage() {
		return LastCounterAttackDamage;
	}
}
