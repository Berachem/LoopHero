package fr.iut.zen.game.elements.equipments;
import java.util.*;
public class Armor extends AbstractEquipment{

  public Armor(String rarety, int level){
	  	super(rarety, level,"Armor", "MaximumHP",new Random().nextInt((100*level - 80*level) + 1) + 80*level);
		getAllStats().remove("MaximumHP");
	}
	

}