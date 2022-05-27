package fr.iut.zen.game.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.elements.cards.Card;
import fr.iut.zen.game.elements.equipments.Equipment;

public class Inventory implements Serializable{
	private final ArrayList<Equipment> list;
	private final int sizeLimit = 12;

	public Inventory(){
		this.list = new ArrayList<Equipment>();
	}
	public void remove(Equipment e){
		Objects.requireNonNull(e);
		list.remove(e);
	}
	public void add(Equipment e){
		Objects.requireNonNull(e);
		if (list.size() < sizeLimit){
			list.add(e);
		}
		else {
			list.remove(0); // the oldest added equipment is deleted
			list.add(e);
		}
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("----Inventory----\n");
		for (Equipment e : list) {
			s.append(e).append("\n");
		}
		
		s.append("-------------\n");
		return s.toString();
	}
	
	public ArrayList<Equipment> getList() {
		return list;
	}
	
	
}