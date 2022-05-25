package fr.iut.zen.game.elements.equipments;
import java.util.*;

abstract class AbstractEquipment implements Equipment{

	  private final Map<String,Integer> stats;
	  private final String rarety;
	  private final int level;
	  private final List<String> allStats;
	  private final String equipmentType;
	  private String BasicStat;
	  private final String ImagePath;



  public AbstractEquipment(String rarety, int level, String equipmentType, String BasicStat, int BasicStatValue){
	  Objects.requireNonNull(rarety);
	  Objects.requireNonNull(equipmentType);
	 
  	  this.rarety = rarety;
  	  this.level = level;
  	  this.equipmentType = equipmentType;
  	  this.ImagePath = "pictures/"+rarety+"_"+equipmentType+".png";
  	  this.stats = new HashMap<String, Integer>();

  	  /* Initialize all stats */
	  this.allStats = new ArrayList<>();
      getAllStats().add("Defense");
      getAllStats().add("Counter");
      getAllStats().add("Vampirism");
      getAllStats().add("Regen");
      getAllStats().add("Evade");

     
	    if (!(equipmentType.equals("Ring"))){
	  		this.stats.put(BasicStat, BasicStatValue);
	  		this.BasicStat = BasicStat;
	    }else{
	
	    	/* If the equipment is a ring, my base stat is random */
	      int peak = new Random().nextInt(allStats.size());
	      System.out.println(allStats);
	      System.out.println(peak);
	      System.out.println(allStats.get(peak));
	
	     this.BasicStat = allStats.get(peak);
	     

	      
	      /* deletes the stat from the list */
	      allStats.remove(this.BasicStat);
	      System.out.println(this.BasicStat);
	      this.stats.put(this.BasicStat, calcValueStat(this.BasicStat));
	     
	    }
	    
	    
	    
    /* actualize all stats from the equipment */
		refreshStatsRarety();
  }


	  /**
	 * @param stat the name of the statistic that we need
	 * @return the value of the given statistic
	 */
	public int calcValueStat(String stat){
		Objects.requireNonNull(stat);
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
	    else { // else if(stat.equals("Evade"))
	      return (int) (8+(level-1)*2);
	    }
	}

  /**
 *Updates the equipment statistics based on his rarity
 */
	public void refreshStatsRarety(){
		System.out.println(equipmentType+ BasicStat);
		int b = stats.get(BasicStat);
   		String bonus1 = getAllStats().get(new Random().nextInt(getAllStats().size()));
		String bonus2 = getAllStats().get(new Random().nextInt(getAllStats().size()));
		String bonus3 = getAllStats().get(new Random().nextInt(getAllStats().size()));

		if (!rarety.equals("Grey")) {
			
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
	}


  
  //ACCESSORS
	public String getEquipmentType() {
		return equipmentType;
	}
	public Map<String,Integer> getStats(){
		return stats;
	}

	public String getImagePath(){
		return ImagePath;
	}


	public List<String> getAllStats() {
		return allStats;
	}


	@Override
	public String toString() {
		return "equipmentType=" + equipmentType+", stats=" + stats + ", rarety=" + rarety+", level="+level ;
	}
	
	

	
	

}
