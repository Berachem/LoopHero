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
	private GridPosition locationBox = new GridPosition(0,0);// Box où il est situé
	private double ressources;
	protected double hp = 100;
	private double damage;
	private double defense;
	private double maximumHP = 160;
	private double counter;
	private double vampirism;
	private double regen;
	private double evade;
	private boolean isAlive;
	// Il doit surement manquer des trucs
	
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

	public void refreshStats(Equipment e){
		Dictionary<String, Integer> stats = e.getStats();
		Enumeration<String> keys = stats.keys();  //enumeration de toute les clés
		while( keys.hasMoreElements() ){
			switch (keys.nextElement()) {
			case "damage": damage+=stats.get(keys.nextElement());
      case "defense": defense+=stats.get(keys.nextElement());
			case "maximumHP": maximumHP+=stats.get(keys.nextElement());
			case "counter": counter+=stats.get(keys.nextElement());
			case "vampirism": vampirism+=stats.get(keys.nextElement());
      case "regen": regen+=stats.get(keys.nextElement());
			case "evade": evade+=stats.get(keys.nextElement());
			}
		}
	}
	
	public void heroOnCampFire() {
		if (hp*1.2>maximumHP) {
			hp = maximumHP;
		}else {
			hp*=1.2;
		}
	}
	
	public void attacked(double dmg) {
		if (hp-dmg<=0) {
			hp=0;
			isAlive=false;
			ImagePath = "pictures/rip.png";
		}else {
			hp-=dmg;
		}
	}

	public double getHp() {
		return hp;
	}

	public boolean isAlive() {
		// TODO Auto-generated method stub
		return isAlive;
	}

	public double getRessources() {
		return ressources;
	}
	
	public void winRessources(double count) {
		ressources+=count;
	}
	
	public List<Card> getCardsList(){
		return hand.getList();
	}
	
	public void addCardsInHand(List<Card> l) {
		for (Card c : l) {
			hand.add(c);
		}
	}
	
	public void addEquipmentsInInventory(List<Equipment> l) {
		for (Equipment e : l) {
			inventory.add(e);
		}
	}
	
	public void healValue(double value) {
		if (hp+value>maximumHP) {
			hp = maximumHP;
		}else {
			hp+=value;
		}
	}
	
	public void healPercentage(double value) {
		if (hp+value>maximumHP) {
			hp = maximumHP;
		}else {
			
			hp+=hp*1+value/100;
		}
	}

	public String getImagePath() {
		return ImagePath;
	}
	
	
	
	
}