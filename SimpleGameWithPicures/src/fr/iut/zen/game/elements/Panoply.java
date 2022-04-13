package fr.iut.zen.game.elements;

import java.util.*;

import fr.iut.zen.game.elements.equipments.Armor;
import fr.iut.zen.game.elements.equipments.Equipment;
import fr.iut.zen.game.elements.equipments.Ring;
import fr.iut.zen.game.elements.equipments.Shield;
import fr.iut.zen.game.elements.equipments.Weapon;  

public class Panoply{
	private final HashMap<String,Equipment> equipedItems;

  public Panoply(){
		HashMap<String,Equipment> dict = new HashMap<String, Equipment>();
		dict.put("Weapon",new Weapon("Grey",0));
		//dict.put("Ring",new Ring("Ring", 0));
		dict.put("Armor",new Armor("Grey", 0));
		dict.put("Shield",new Shield("Grey", 0));
		this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
	Objects.requireNonNull(e);
    equipedItems.put(e.getEquipmentType(), e);
  }
	
	public HashMap<String,Equipment> getEquipedItems(){
		return equipedItems;
	}
	
}