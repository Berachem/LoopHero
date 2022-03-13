import java.util.Objects;
import fr.iut.zen.game.elements.cards.*;
import fr.iut.zen.game.elements.equipments.*;

import fr.iut.zen.game.elements.equipments.Equipment;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Hero{
	private final String name;
	private final Inventory inventory;
	private final Hand hand;
	private final Panoply panoply;
	Box locationBox;// Box où il est situé
	int hp;
	int damage;
	int defense;
	int maximumHP;
	int counter;
	int vampirism;
	int regen;
	int evade;
	// Il doit surement manquer des trucs
	
	public Hero(String name, Box StartBox){
		Objects.requireNonNull(name);
		Objects.requireNonNull(StartBox);
		this.name = name;
		this.locationBox = StartBox;
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
		Dictionary<String,Integer> stats = e.stats();
    for (String key: stats.keys()){
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
	
}