package fr.iut.zen.game.elements;

import java.io.Serializable;
import java.util.Objects;

public class Stats implements Serializable {
	private double damage;
	private double defense;
	private double maximumHP = 250;
	private double counter;
	private double vampirism;
	private double regen;
	private double evade;
	
	
	public Stats(double damage, double defense, double maximumHP, double counter, double vampirism, double regen,
			double evade) {
		this.damage = damage;
		this.defense = defense;
		this.maximumHP = maximumHP;
		this.counter = counter;
		this.vampirism = vampirism;
		this.regen = regen;
		this.evade = evade;
	}


	public double getDamage() {
		return damage;
	}


	public void addDamage(double damage) {
		this.damage += damage;
		if (this.damage <0) {
			this.damage=0;
		}
	}


	public double getDefense() {
		return defense;
	}


	public void addDefense(double defense) {
		this.defense += defense;
		if (this.defense <0) {
			this.defense=0;
		}
	}


	public double getMaximumHP() {
		return maximumHP;
	}


	public void addMaximumHP(double maximumHP) {
		this.maximumHP += maximumHP;
		if (this.maximumHP <0) {
			this.maximumHP=0;
		}
	}
	
	public void mutiplyMaximumHP(double maximumHP) {
		this.maximumHP *= maximumHP;
	}


	public double getCounter() {
		return counter;
	}


	public void addCounter(double counter) {
		this.counter += counter;
		if (this.counter <0) {
			this.counter=0;
		}
	}


	public double getVampirism() {
		return vampirism;
	}


	public void addVampirism(double vampirism) {
		this.vampirism += vampirism;
		if (this.vampirism <0) {
			this.vampirism=0;
		}
	}


	public double getRegen() {
		return regen;
	}


	public void addRegen(double regen) {
		this.regen += regen;
		if (this.regen <0) {
			this.regen=0;
		}
	}


	public double getEvade() {
		return evade;
	}


	public void addEvade(double evade) {
		this.evade += evade;
		if (this.evade <0) {
			this.evade=0;
		}
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(counter, damage, defense, evade, maximumHP, regen, vampirism);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stats other = (Stats) obj;
		return Double.doubleToLongBits(counter) == Double.doubleToLongBits(other.counter)
				&& Double.doubleToLongBits(damage) == Double.doubleToLongBits(other.damage)
				&& Double.doubleToLongBits(defense) == Double.doubleToLongBits(other.defense)
				&& Double.doubleToLongBits(evade) == Double.doubleToLongBits(other.evade)
				&& Double.doubleToLongBits(maximumHP) == Double.doubleToLongBits(other.maximumHP)
				&& Double.doubleToLongBits(regen) == Double.doubleToLongBits(other.regen)
				&& Double.doubleToLongBits(vampirism) == Double.doubleToLongBits(other.vampirism);
	}


	@Override
	public String toString() {
		return " (damage=" + damage + ", defense=" + defense + ", maximumHP=" + maximumHP + ", counter=" + counter
				+ ", vampirism=" + vampirism + ", regen=" + regen + ", evade=" + evade + ")";
	}
	
	
	
	
	
	
}
