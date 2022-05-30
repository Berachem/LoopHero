package fr.iut.zen.game.elements.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.tiles.MeadowTile;
import fr.iut.zen.game.elements.tiles.Tile;
import fr.iut.zen.game.elements.tiles.VillageTile;

public class Village implements Card,Serializable {
	private final String villagePATH = "pictures/VillageCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return villagePATH;
	}

	@Override
	public String getType() {

		return "Road";
	}


	
	
	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new VillageTile(p);
	}
	
	@Override
	
	public String toString() {
		return "Village Card";
	}


}


