package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.ScorchWorm;
import fr.iut.zen.game.elements.tiles.RuinsTile;
import fr.iut.zen.game.elements.tiles.Tile;
import fr.iut.zen.game.elements.tiles.VillageTile;

public class Ruins implements Card,Serializable {
	private final String ruinsPATH = "pictures/RuinsCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return ruinsPATH;
	}

	@Override
	public String getType() {

		return "Road";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new RuinsTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Ruins Card";
	}

	


}


