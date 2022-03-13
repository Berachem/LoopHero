import java.util.Random;
import java.util.*;
public class Armor extends AbstractEquipment{
  
  public Armor(String rarety, int level){
    super(rarety, level);
		Dictionary<String,Integer> stats = new Hashtable();
		stats.put("MaximumHP", new Random().nextInt((100*level - 80*level) + 1) + 80*level);
		refreshStatsRarety();
		allStats.remove("MaximumHP");
	}
  public void refreshStatsRarety(){
		int hp = stats.get("MaximumHP");
    String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      stats.put("MaximumHP",hp*=0.9);
			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/3);
    }
    else if(rarety.equals("Yellow")){
			int newHp = hp *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("MaximumHP",newHp);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);
			
    }else{
			int newHP = hp *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("MaximumHP",newHP);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);

			stats.put(allStats.get(bonus3), calcValueStat(allStats.get(bonus3))/4);
    }
  }
}