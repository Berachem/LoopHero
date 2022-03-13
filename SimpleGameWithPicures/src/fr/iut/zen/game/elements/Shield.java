import java.util.Random;
import java.util.*;
public class Shield extends AbstractEquipment{
  
  public Shield(String rarety, int level){
    super(rarety, level);
		Dictionary<String,Integer> stats = new Hashtable();
		stats.put("Defense", 4*level);
		refreshStatsRarety();
		allStats.remove("Defense");
	}
  public void refreshStatsRarety(){
		int df = stats.get("Defense");
    String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      stats.put("Defense",df*=0.9);
			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/3);
    }
    else if(rarety.equals("Yellow")){
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Defense",newDf);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);
			
    }else{
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("Defense",newf);

			stats.put(allStats.get(bonus1), calcValueStat(allStats.get(bonus1))/2);

			String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
			stats.put(allStats.get(bonus2), calcValueStat(allStats.get(bonus2))/2);

			stats.put(allStats.get(bonus3), calcValueStat(allStats.get(bonus3))/4);
    }
  }

}
