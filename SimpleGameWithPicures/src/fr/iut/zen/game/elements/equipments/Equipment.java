package fr.iut.zen.game.elements.equipments;
import java.util.*;

import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Rock;  

public interface Equipment {
	public void refreshStatsRarety();
	public String getEquipmentType();
	public Map<String,Integer> getStats();
	public String getImagePath();
	
	/**
	 * @param the number of completed loops
	 * @return an arrayList of the three possible equipment that you can have ( randomize the color with the given probability)
	 */
	public static List<AbstractEquipment> catalog(int loopCount) {
		String color = "";
		int random = new Random().nextInt(100) + 1;
		System.out.println(random);
		if (random <= 40) {
			color = "Grey";
		}
		else if (random <= 65) {
			color = "Blue";
		}
		else if (random <= 85) {
			color = "Yellow";
		}
		else {
			color = "Orange";
		}
	
		
		return Arrays.asList(new Armor(color,loopCount), new Shield(color,loopCount), new Weapon(color,loopCount) ,new Ring(color, loopCount));
	}
}
