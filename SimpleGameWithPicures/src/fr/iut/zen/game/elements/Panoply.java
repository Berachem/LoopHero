package fr.iut.zen.game.elements;

import java.util.*;

import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;  

public class Panoply{
	private final Map<String,Equipment> equipedItems;

  public Panoply(){
		Map<String,Equipment> dict = new HashMap<String, Equipment>();
		dict.put("Weapon",new Armor("Weapon", 0));
		dict.put("Ring",new Armor("Ring", 0));
		dict.put("Armor",new Armor("Armor", 0));
		dict.put("Shield",new Armor("Shield", 0));
		this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
	Objects.requireNonNull(e);
    equipedItems.put(e.getEquipmentType(), e);
  }
	
}