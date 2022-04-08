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
	private final Panoply panoply;
	protected double hp = 250;
	private Stats herostats;
	private boolean isAlive;
	private final Map<String,Integer> ressources;

	
	public Hero(String name){
		Objects.requireNonNull(name);
		this.name = name;
		isAlive=true;
		this.panoply = new Panoply();
		this.inventory= new Inventory();
		this.hand = new Hand();
		hand.add(new Meadow());
		hand.add(new Rock());
		hand.add(new Grove());
		this.ImagePath = "pictures/HERO.png";
		ressources = new HashMap<>();
		herostats = new Stats(5, 0, 250, 0, 0, 0, 0);//double damage, double defense, double maximumHP, double counter, double vampirism, double regen, double evade
		
		
		
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
			case "damage": herostats.addDamage(stats.get(key));
      case "defense": herostats.addDefense(stats.get(key));
			case "maximumHP": herostats.addMaximumHP(stats.get(key));
			case "counter": herostats.addCounter(stats.get(key));
			case "vampirism": herostats.addVampirism(stats.get(key));
      case "regen": herostats.addRegen(stats.get(key));
			case "evade": herostats.addRegen(stats.get(key));
			}
		}
	}
	
	/**
	 * heals the hero by 20% of his current Hp
	 */
	public void heroOnCampFire() {
		if (hp*1.2>herostats.getMaximumHP()) {
			hp = herostats.getMaximumHP();
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

	
	public void winRessources(ArrayList<String> ressourcesDroped) {
		
		for (String r: ressourcesDroped) {
			if (ressources.get(r) == null) {
				ressources.put(r, 1);
			}else {
				ressources.put(r, ressources.get(r)+1);
			}
			
		}
		
		
		
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
		if (hp+value>herostats.getMaximumHP()) {
			hp = herostats.getMaximumHP();
		}else {
			hp+=value;
		}
	}
	
	/**heals the hero if his HP aren't already full
	 * @param value the percentage of hp that we want to heal
	 */
	public void healPercentage(double value) {
		if (hp+value>herostats.getMaximumHP()) {
			hp = herostats.getMaximumHP();
		}else {
			
			hp+=hp*1+value/100;
		}
	}
	
	
	
	/** increase a given percentage of MaximumHP
	 * @param value the percentage of hp increasing the base MaximumHP
	 */
	public void increaseMaximumHpPercentage(double value) {
		herostats.mutiplyMaximumHP( (1+value/100));
		System.out.println("fdsfdsfdsfsdfsdfdsfsdfsdfdfdsfds"+herostats.getMaximumHP());
	}
	
	
	//ACCESSORS
	
	public double getHp() {
		return hp;
	}
	
	public double getMaxHp() {
		return herostats.getMaximumHP();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public Map<String,Integer> getRessources() {
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

	public Inventory getInventory() {
		return inventory;
	}

	public Panoply getPanoply() {
		return panoply;
	}
	
	
	
	
}