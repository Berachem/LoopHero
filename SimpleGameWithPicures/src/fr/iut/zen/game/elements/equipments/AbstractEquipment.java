package fr.iut.zen.game.elements.equipments;
import java.util.*;

abstract class AbstractEquipment implements Equipment{
  
	  public Dictionary<String,Integer> stats;
	  private final String rarety;
	  private final int level;
	  protected final List<String> allStats;
	  private final String equipmentType;
	  private final String BasicStat;


  public AbstractEquipment(String rarety, int level, String equipmentType, String BasicStat, int BasicStatValue){
	  	this.rarety = Objects.requireNonNull(rarety);
	  	this.level = level;
		this.allStats = new ArrayList<>();
		this.equipmentType = equipmentType;
		this.stats.put(BasicStat, BasicStatValue);
		this.BasicStat = BasicStat;
		if (equipmentType.equals("Ring")) {
			BasicStat=allStats.get(new Random().nextInt(allStats.size()));
			allStats.remove(BasicStatValue);
		}
		allStats.add("Defense");
		allStats.add("Counter");
		allStats.add("Vampirism");
		allStats.add("Regen");
		allStats.add("Evade");
		refreshStatsRarety();
  }


  public int calcValueStat(String stat){
	  if (stat.equals("Counter")){
      return  8 + (level - 1) * 4;
    }
    else if(stat.equals("Defense")){
      return (int) (level* 1.5);
    }
    else if(stat.equals("Vampirism")){
      return (int) (8 + (level - 1) * 1.5);
    }
    else if(stat.equals("Regen")){
      return (int) (level *0.6);
    }
    else if(stat.equals("Evade")){
      return (int) (8+(level-1)*2);
    }
	return 0;
	} 

  public void refreshStatsRarety(){
		int b = stats.get(BasicStat);
   		String bonus1 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus2 = allStats.get(new Random().nextInt(allStats.size()));
		String bonus3 = allStats.get(new Random().nextInt(allStats.size()));
		
		
		if (rarety.equals("Blue")){
      		stats.put(BasicStat,b*=0.9);
			stats.put(bonus1, (int) (calcValueStat(bonus1)/3));
    }
    else {
			int newBaseStat = b *(new Random().nextInt((100 - 80) + 1) + 80/100);
			stats.put(BasicStat,newBaseStat);

			stats.put(bonus1, (int) (calcValueStat(bonus1)/2));

			stats.put(bonus2, (int) (calcValueStat(bonus2)/2));
			
			if (rarety.equals("Orange")) {
				stats.put(bonus3, (int) (calcValueStat(bonus3)/4));
			}
    }
  }


	public String getEquipmentType() {
		return equipmentType;
	}
	public Dictionary<String,Integer> getStats(){
		return stats;
	}
	
	
  
}