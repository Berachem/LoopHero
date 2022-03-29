package fr.iut.zen.game.elements;

import fr.iut.zen.game.Cell;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.equipments.Equipment;

import java.util.*;

public class Hero{
	private String ImagePath;
	private final String name;
	private final Inventory inventory;
	private final Hand hand;
	//private final Panoply panoply;
	private double ressources;
	protected double hp = 250;
	private double damage;
	private double defense;
	private double maximumHP = 250;
	private double counter;
	private double vampirism;
	private double regen;
	private double evade;
	private boolean isAlive;

	
	public Hero(String name){
		Objects.requireNonNull(name);
		this.name = name;
		isAlive=true;
		//this.panoply = new Panoply();
		this.inventory= new Inventory();
		this.hand = new Hand();
		hand.add(new Meadow());
		hand.add(new Rock());
		hand.add(new Grove());
		this.ImagePath = "pictures/HERO.png";
		
		
	}

	public void equipItem(Equipment e){
		Objects.requireNonNull(e);
		//inventory.remove(e);
		//panoply.equipItem(e);
		refreshStats(e);
	}

	/** refreshed the hero's stats after equipping an equipement 
	 * @param e the equipped equipment
	 */
	public void refreshStats(Equipment e){
		Objects.requireNonNull(e);
		Map<String, Integer> stats = e.getStats();
		Set<String> keys = stats.keySet();  //enumeration de toute les clés
		for( String key : keys ){
			switch (key) {
			case "damage": damage+=stats.get(key);
      case "defense": defense+=stats.get(key);
			case "maximumHP": maximumHP+=stats.get(key);
			case "counter": counter+=stats.get(key);
			case "vampirism": vampirism+=stats.get(key);
      case "regen": regen+=stats.get(key);
			case "evade": evade+=stats.get(key);
			}
		}
	}
	
	/**
	 * heals the hero by 20% of his current Hp
	 */
	public void heroOnCampFire() {
		if (hp*1.2>maximumHP) {
			hp = maximumHP;
		}else {
			hp*=1.2;
		}
	}
	
	
	
	/** lowers the hero hp, if the HP = 0 , the hero is no longer alive and a grave replaces him
	 * @param dmg the amount of damage taken by the hero
	 */
	public void attacked(double dmg) {
		if (hp-dmg<=0) {
			hp=0;
			isAlive=false;
			ImagePath = "pictures/rip.png";
		}else {
			hp-=dmg;
		}
	}

	
	public void winRessources(double count) {
		ressources+=count;
	}
	
	
	public void addCardsInHand(List<Card> l) {
		for (Card c : l) {
			hand.add(c);
		}
	}
	
	public void addEquipmentsInInventory(List<Equipment> l) {
		Objects.requireNonNull(l);
		for (Equipment e : l) {
			inventory.add(e);
		}
	}
	
	/**heals the hero if his HP aren't already full
	 * @param value the amount of hp that we want to heal
	 */
	public void healValue(double value) {
		if (hp+value>maximumHP) {
			hp = maximumHP;
		}else {
			hp+=value;
		}
	}
	
	/**heals the hero if his HP aren't already full
	 * @param value the percentage of hp that we want to heal
	 */
	public void healPercentage(double value) {
		if (hp+value>maximumHP) {
			hp = maximumHP;
		}else {
			
			hp+=hp*1+value/100;
		}
	}
	
	
	
	/** increase a given percentage of MaximumHP
	 * @param value the percentage of hp increasing the base MaximumHP
	 */
	public void increaseMaximumHpPercentage(double value) {
		maximumHP*= (1+value/100);
		System.out.println("fdsfdsfdsfsdfsdfdsfsdfsdfdfdsfds"+maximumHP);
	}
	
	
	//ACCESSORS
	
	public double getHp() {
		return hp;
	}
	
	public double getMaxHp() {
		return maximumHP;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public double getRessources() {
		return ressources;
	}
	

	public String getImagePath() {
		return ImagePath;
	}

	public Hand getHand() {
		return hand;
	}
	
	public List<Card> getCardsList(){
		return hand.getList();
	}
	
}