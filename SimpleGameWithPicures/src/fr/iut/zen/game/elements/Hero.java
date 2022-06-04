package fr.iut.zen.game.elements;

import fr.iut.zen.game.Cell;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Battlefield;
import fr.iut.zen.game.elements.cards.Beacon;
import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.cards.Cemetery;
import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Oblivion;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.cards.Ruins;
import fr.iut.zen.game.elements.cards.SpiderCocoon;
import fr.iut.zen.game.elements.cards.VampireMansion;
import fr.iut.zen.game.elements.cards.Village;
import fr.iut.zen.game.elements.cards.WheatFields;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Shield;
import fr.iut.zen.game.elements.equipments.Weapon;

import java.io.Serializable;
import java.util.*;

public class Hero implements Serializable{
	private String ImagePath;
	private final String name;
	private final Inventory inventory;
	private final Hand hand;
	private final Panoply panoply;
	protected double hp = 250;
	private GridPosition pos = null;
	private Stats herostats;
	private boolean isAlive;
	private int LastCounterAttackDamage = 0;
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
		hand.add(new Cemetery());
		hand.add(new Ruins());
		this.ImagePath = "pictures/HERO.png";
		ressources = new HashMap<>(){{
	        put("Stable Branches", 11);
	        put("Stable Wood", 0);
	        put("Preserved Pebble", 0);
	        put("Preserved Rock", 0);
	        put("Scrap Metal", 0);
	        put("Stable Metal", 0);
	        put("Ration", 0);
	        put("Food Supply", 0); 
	        put("Pitiful Remain", 0);
	        put("Orb of Afterlife", 0);
	        put("Craft Fragment", 0);
	        put("Orb of Crafts", 0);
	        put("Living Fabric", 0); 
	        put("Orb of Evolutions", 0); 
	        put("Shapeless Mass", 0);
	        put("Orb of Unity", 0);
	        
	    }};
		herostats = new Stats(5, 0, 250, 0, 0, 0, 0);//double damage, double defense, double maximumHP, double counter, double vampirism, double regen, double evade
		
	}

	
	
	public void equipItem(Equipment e){
		Objects.requireNonNull(e);
		//inventory.remove(e);
		Equipment PreviousItem = getPanoply().getEquipedItems().get(e.getEquipmentType());
		if (PreviousItem!=null && !PreviousItem.isBasic()) {
			removeStatsItemOnHero(PreviousItem);
		}
		panoply.equipItem(e);
		refreshStats(e);
	}

	/**
	 * remove all the stats the hero gained with the removed equipment
	 * @param previousItem the removed item
	 */
	private void removeStatsItemOnHero(Equipment previousItem) {
		Objects.requireNonNull(previousItem);
		Map<String, Integer> stats = previousItem.getStats();
		Set<String> keys = stats.keySet();  //key enumeration 
		for( String key : keys ){
			switch (key) {
				case "Damage": herostats.addDamage(-stats.get(key)); break;
				case "Defense": herostats.addDefense(-stats.get(key));break;
				case "MaximumHP": herostats.addMaximumHP(-stats.get(key)); break;
				case "Counter": herostats.addCounter(-stats.get(key));break;
				case "Vampirism": herostats.addVampirism(-stats.get(key)); break;
				case "Regen": herostats.addRegen(-stats.get(key));break;
				case "Evade": herostats.addEvade(-stats.get(key));break;
			}
		}
	}

	/** refreshed the hero's stats after equipping an equipement 
	 * @param e the equipped equipment
	 */
	public void refreshStats(Equipment e){
		Objects.requireNonNull(e);
		Map<String, Integer> stats = e.getStats();
		Set<String> keys = stats.keySet();  //enumeration de toute les clÃ©s
		
		for( String key : keys ){
			switch (key) {
				case "Damage": herostats.addDamage(stats.get(key)); break;
				case "Defense": herostats.addDefense(stats.get(key));break;
				case "MaximumHP": herostats.addMaximumHP(stats.get(key)); break;
				case "Counter": herostats.addCounter(stats.get(key));break;
				case "Vampirism": herostats.addVampirism(stats.get(key)); break;
				case "Regen": herostats.addRegen(stats.get(key));break;
				case "Evade": herostats.addEvade(stats.get(key));break;
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
	public int attacked(Mobs m) {
		if (hp-m.attack() + herostats.getDefense()<=0) {
			hp=0;
			isAlive=false;
			ImagePath = "pictures/rip.png";
			return -1; // il est mort après l'attaque
		}else {
			
			double random = new Random().nextDouble(1.0);
			if (random>herostats.getEvade()/100) { // Il esquive pas...
				if (herostats.getDefense()<m.attack()) {
					hp-=(m.attack()-herostats.getDefense());
				}else {
					herostats.addDefense(-m.attack());
				}
				LastCounterAttackDamage = 0;
				counterAttack(m);
				return 1; // il a pris l'attaque et a contre attaqué
			}
			return 0; // il a equivé l'attaque
		}
		
				

	}
	
	public void counterAttack(Mobs m) {
		double random = new Random().nextDouble(100);
		if (random<herostats.getCounter()) { // Il contre attque
			m.counterAttacked(6);
			
			LastCounterAttackDamage = 6;
		}
	}
	
	public void counterAttacked(int dmg) {
		if (hp-dmg<=0) {
			hp = 0;
			isAlive=false;
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

	public double attack() {
		healValue(herostats.getDamage()*(herostats.getVampirism()/100));
		return herostats.getDamage();
	}

	public Stats getHerostats() {
		return herostats;
	}

	public int getLastCounterAttackDamage() {
		return LastCounterAttackDamage;
	}

	public GridPosition getPos() {
		return pos;
	}

	public void setPos(GridPosition pos) {
		this.pos = pos;
	}
	
	

	
	
	
	
	
	
}