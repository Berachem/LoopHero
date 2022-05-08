package fr.iut.zen.game.elements.equipments;
import java.util.*;
public class Armor extends AbstractEquipment{

  public Armor(String rarety, int level){
	  	super(rarety, level,"Armor", "MaximumHP",4*level);
		getAllStats().remove("MaximumHP");
	}
  
  
	
  	
}