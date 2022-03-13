package fr.iut.zen.game.elements;

import java.util.Objects;

import fr.iut.zen.game.Cell;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.equipments.Equipment;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Hero{
	private final String name;
	private final Inventory inventory;
	private final Hand hand;
	private final Panoply panoply;
	private GridPosition locationBox = new GridPosition(0,0);// Box où il est situé
	int hp;
	int damage;
	int defense;
	int maximumHP;
	int counter;
	int vampirism;
	int regen;
	int evade;
	// Il doit surement manquer des trucs
	
	public Hero(String name){
		Objects.requireNonNull(name);
		this.name = name;
		this.panoply = new Panoply();
		this.inventory= new Inventory();
		this.hand = new Hand();
	}

	public void equipItem(Equipment e){
		Objects.requireNonNull(e);
		inventory.remove(e);
		panoply.equipItem(e);
		refreshStats(e);
	}

	public void refreshStats(Equipment e){
		Dictionary<String,Integer> stats = e.getStats();
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
	
}