package fr.iut.zen.game.elements.equipments;
import java.util.*;

	public class Weapon extends AbstractEquipment{
	//private final Dictionary<String,Integer> stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/3);stats;
	private Dictionary<String,Integer> stats;
  
	public Weapon(String rarety, int level){
    super(rarety, level);
		Dictionary<String,Integer> stats = new Hashtable();
		stats.put("Damage", new Random().nextInt((6*level - 4*level) + 1) + 4*level);
		refreshStatsRarety();
		allStats.remove("Damage");
	}

	@Override
	public Dictionary stats() {
		// TODO Auto-generated method stub
		return null;
	}


 
}