package fr.iut.zen.game.elements.equipments;
import java.util.*;
public class Armor extends AbstractEquipment{
	
	private Dictionary<String,Integer> stats;

  public Armor(String rarety, int level){
    super(rarety, level);
		Dictionary<String,Integer> stats = new Hashtable();
		stats.put("MaximumHP", new Random().nextInt((100*level - 80*level) + 1) + 80*level);
		refreshStatsRarety();
		allStats.remove("MaximumHP");
	}
	public void refreshStatsRarety(){
		int df = stats.get("MaximumHP");
   		String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      		stats.put("MaximumHP",df*=0.9);
			stats.put(bonus1, (int) (calcValueStat(bonus1)/3));
    }
    else if(rarety.equals("Yellow")){
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("MaximumHP",newDf);

			stats.put(bonus1, (int) (calcValueStat(bonus1)/2));

			stats.put(bonus2, (int) (calcValueStat(bonus2)/2));
			
    }else{
			int newDf = df *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put("MaximumHP",newDf);

			stats.put(bonus1, (int) (calcValueStat(bonus1)/2));

			stats.put(bonus2, (int) (calcValueStat(bonus2)/2));

			stats.put(bonus3, (int) (calcValueStat(bonus3)/4));
    }
  }
  

}