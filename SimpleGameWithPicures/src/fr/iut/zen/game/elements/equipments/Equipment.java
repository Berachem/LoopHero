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
	public static List<AbstractEquipment> catalog(int loopCount) {
		String color = "";
		int random = new Random().nextInt(100) + 1;
		System.out.println(random);
		if (random <= 35) {
			color = "Grey";
		}
		else if (random <= 60) {
			color = "Blue";
		}
		else if (random <= 80) {
			color = "Yellow";
		}
		else if (random <= 95) {
			color = "Orange";
		}
		else {
			color = "Basic";
		}
		
		return Arrays.asList(new Armor(color,loopCount), new Shield(color,loopCount), new Weapon(color,loopCount));
	}
}
