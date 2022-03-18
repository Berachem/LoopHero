package fr.iut.zen.game.elements;

import java.util.*;

import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;  

public class Panoply{
	private final Dictionary<String,Equipment> equipedItems;

  public Panoply(){
		Dictionary<String,Equipment> dict = new Hashtable<String, Equipment>();
		dict.put("Weapon",new Armor("s", 0));
		dict.put("Ring",new Armor("s", 0));
		dict.put("Armor",new Armor("s", 0));
		dict.put("Shield",new Armor("s", 0));
    this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
    equipedItems.put(e.getEquipmentType(), e);
  }
	
}