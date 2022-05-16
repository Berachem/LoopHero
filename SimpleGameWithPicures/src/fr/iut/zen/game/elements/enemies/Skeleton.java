package fr.iut.zen.game.elements.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.Stats;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Skeleton implements Mobs {

	private final String SkeletonPATH = "pictures/Skeleton.png";
	private final GridPosition locationSkeleton;
	private final double DropCardChance = 0.15;
	private final double DropEquipmentChance = 0.85;
	private final double baseHealth = 12;
	private final double baseDamage = 9;
	private double health;
	private Stats stats;
	private int LastCounterAttackDamage;

	
	public Skeleton(GridPosition location, int LoopCount) {
		Objects.requireNonNull(location);
		
		//base × lvl × 0.95 × (1 + (lvl − 1) × 0.02),
		health=baseHealth*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		double damage = baseDamage*LoopCount*0.95*(1+(LoopCount-1)*0.02);
		this.locationSkeleton = location;
		stats = new Stats(damage, 0, 0, 0, 0, 0, 5);
		
		//double damage, double defense, double maximumHP, double counter, 
		//double vampirism, double regen, double evade
	}


	@Override
	public String getImagePath() {
		return SkeletonPATH;
	}


	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int attacked(Hero hero) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void counterAttacked(int dmg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public double getLastCounterAttackDamage() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void counterAttack(Hero h) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isInPosition(GridPosition p) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<String> dropRessources() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Card> dropCards() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Equipment> dropEquipments(int loopCount) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double getHp() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Stats getStats() {
		// TODO Auto-generated method stub
		return null;
	}
}
