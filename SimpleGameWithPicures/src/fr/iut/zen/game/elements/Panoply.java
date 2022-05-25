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
		//the hero already have basic equipment at the beginning of the game
		dict.put("Weapon",new Weapon("Basic",0));
		dict.put("Armor",new Armor("Basic", 0));
		dict.put("Shield",new Shield("Basic", 0));
		dict.put("Ring",new Ring("Basic", 0));
		this.equipedItems = dict;
  }
	public void equipItem(Equipment e) {
	Objects.requireNonNull(e);

    equipedItems.put(e.getEquipmentType(), e);
  }
	
	public HashMap<String,Equipment> getEquipedItems(){
		return equipedItems;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("----Panoply----\n");
		for (String c : equipedItems.keySet()) {
			s.append(equipedItems.get(c)).append("\n");
		}
		
		s.append("-------------\n");
		return s.toString();
	}
	
	
}