package fr.iut.zen.game.elements;

import java.util.*;

import fr.iut.zen.game.elements.equipments.Equipment;  

public class Panoply{
	private final Dictionary<String,Integer> equipedItems;

  public Panoply(){
		Dictionary<String,Integer> dict = new Dictionary<String, Integer>();
		dict.put("Weapon",null);
		dict.put("Ring",null);
		dict.put("Armor",null);
		dict.put("Shield",null);
    this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
    equipedItems.put(e.equipmentType(), e);
  }
	
}