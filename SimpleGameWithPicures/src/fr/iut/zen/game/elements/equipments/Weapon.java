package fr.iut.zen.game.elements.equipments;
import java.util.*;

	public class Weapon extends AbstractEquipment{

  
	public Weapon(String rarety, int level){
    super(rarety, level,"Weapon","Damage", new Random().nextInt((6*level - 4*level)+1) + 4*level);

	}

 
}