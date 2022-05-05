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
		return Arrays.asList(new Armor("Grey",loopCount), new Shield("Grey",loopCount), new Weapon("Grey",loopCount));
	}
}