package fr.iut.zen.game.elements.equipments;
import java.util.Objects;
import java.util.List;
import java.util.*;
import java.util.ArrayList;

abstract class AbstractEquipment implements Equipment{
  private Dictionary<String,Integer> stats;
  final String rarety;
  final int level;
	final List<String> allStats;


  public AbstractEquipment(String rarety, int level){
    this.rarety = Objects.requireNonNull(rarety);
		this.level = level;
		this.allStats = new ArrayList<>();
		allStats.add("Defense");
		allStats.add("Counter");
		allStats.add("Vampirism");
		allStats.add("Regen");
		allStats.add("Evade");
  }


  public double calcValueStat(String stat){
	  if (stat.equals("Counter")){
      return  8 + (level - 1) * 4;
    }
    else if(stat.equals("Defense")){
      return level* 1.5;
    }
    else if(stat.equals("Vampirism")){
      return 8 + (level - 1) * 1.5;
    }
    else if(stat.equals("Regen")){
      return level *0.6;
    }
    else if(stat.equals("Evade")){
      return 8+(level-1)*2;
    }
	return 0;
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