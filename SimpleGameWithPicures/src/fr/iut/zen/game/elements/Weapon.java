import java.util.Random;
import java.util.*;

	public class Weapon extends AbstractEquipment{
	//private final Dictionary<String,Integer> stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/3);stats;
	
  
	public Weapon(String rarety, int level){
    super(rarety, level);
		Dictionary<String,Integer> stats = new Hashtable();
		stats.put("Damage", new Random().nextInt((6*level - 4*level) + 1) + 4*level);
		refreshStatsRarety();
		allStats.remove("Damage");
	}
  public void refreshStatsRarety(){
		int dmg = stats.get("Damage");
    String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      stats.put("Damage",dmg*=0.9);
			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/3);
    }
    else if(rarety.equals("Yellow")){
			int newDmg = dmg *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Damage",newDmg);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);
			
    }else{
			int newDmg = dmg *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Damage",newDmg);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);

			stats.put(allStats.get(bonus3), calcValueStat(allStats.get(bonus3))/4);
    }
  }

 
}