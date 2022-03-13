package fr.iut.zen.game.elements;

import java.util.*;

import fr.iut.zen.game.elements.equipments.Equipment;  

public class Panoply{
	private final Dictionary<String,Equipment> equipedItems;

  public Panoply(){
		Dictionary<String,Equipment> dict = new Hashtable();
		dict.put("Weapon",null);
		dict.put("Ring",null);
		dict.put("Armor",null);
		dict.put("Shield",null);
    this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
    equipedItems.put(e.getEquipmentType(), e);
  }
	
}