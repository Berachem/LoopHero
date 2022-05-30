package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;
import fr.iut.zen.game.elements.tiles.GroveTile;
import fr.iut.zen.game.elements.tiles.Tile;

public class Grove implements Card,Serializable {

	private final String grovePATH = "pictures/GroveCard.png";
	
	@Override
	public String getImagePath() {
		
		return grovePATH;
	}


	@Override
	public String getType() {
		return "Road";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new GroveTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Grove Card";
	}
	

	
	


}


