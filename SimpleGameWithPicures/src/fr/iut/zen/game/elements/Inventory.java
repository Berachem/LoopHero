package fr.iut.zen.game.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.elements.equipments.Equipment;

public class Inventory{
	private final List<Equipment> list;
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
			list.remove(0); // On supprime le plus ancien �quipement qui traine
			list.add(e);
		}
	}
	@Override
	public String toString() {
		return "Inventory [list=" + list + "]";
	}
	
	
}