package fr.iut.zen.game.elements;

public class Stats {
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
	}


	public double getDefense() {
		return defense;
	}


	public void addDefense(double defense) {
		this.defense += defense;
	}


	public double getMaximumHP() {
		return maximumHP;
	}


	public void addMaximumHP(double maximumHP) {
		this.maximumHP += maximumHP;
	}


	public double getCounter() {
		return counter;
	}


	public void addCounter(double counter) {
		this.counter += counter;
	}


	public double getVampirism() {
		return vampirism;
	}


	public void addVampirism(double vampirism) {
		this.vampirism += vampirism;
	}


	public double getRegen() {
		return regen;
	}


	public void addRegen(double regen) {
		this.regen += regen;
	}


	public double getEvade() {
		return evade;
	}


	public void addEvade(double evade) {
		this.evade += evade;
	}
	
	
	
	
}
