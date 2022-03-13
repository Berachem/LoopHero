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

	public void refreshStatsRarety(){
		int df = stats.get("Damage");
   		String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      		stats.put("Damage",df*=0.9);
			stats.put(bonus1, (int) (calcValueStat(bonus1)/3));
    }
    else if(rarety.equals("Yellow")){
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Damage",newDf);

			stats.put(bonus1, (int) (calcValueStat(bonus1)/2));

			stats.put(bonus2, (int) (calcValueStat(bonus2)/2));
			
    }else{
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Damage",newDf);

			stats.put(bonus1, (int) (calcValueStat(bonus1)/2));

			stats.put(bonus2, (int) (calcValueStat(bonus2)/2));

			stats.put(bonus3, (int) (calcValueStat(bonus3)/4));
    }
  }



 
}