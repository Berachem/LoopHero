import java.util.*;  

public class Panoply{
	private final Dictionary<String,Integer> equipedItems;

  public Panoply(){
		Dictionary<String,Integer> dict = new Hashtable();
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