package fr.iut.zen.game.elements.equipments;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

abstract class AbstractEquipment implements Equipment{
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
}