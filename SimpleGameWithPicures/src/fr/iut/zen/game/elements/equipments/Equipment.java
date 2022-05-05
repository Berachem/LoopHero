package fr.iut.zen.game.elements.equipments;
import java.util.*;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.cards.Grove;
import fr.iut.zen.game.elements.cards.Meadow;
import fr.iut.zen.game.elements.cards.Rock;
import fr.iut.zen.game.elements.tiles.Tile;  

public interface Equipment {
	public void refreshStatsRarety();
	public String getEquipmentType();
	public Map<String,Integer> getStats();
	public String getImagePath();
	public static List<AbstractEquipment> catalog() {
		return Arrays.asList(new Armor("Grey",0), new Shield("Grey",0), new Weapon("Grey",0));
	}
}