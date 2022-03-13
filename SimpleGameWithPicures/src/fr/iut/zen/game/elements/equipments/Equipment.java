package fr.iut.zen.game.elements.equipments;
import java.util.*;  

public interface Equipment{
	public void refreshStatsRarety();
	public String getEquipmentType();
	public Dictionary<String,Integer> getStats();
	public String getImagePath();
}