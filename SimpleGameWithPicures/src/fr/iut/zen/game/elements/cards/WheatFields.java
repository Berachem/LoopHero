package fr.iut.zen.game.elements.cards;

import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.tiles.Tile;
import fr.iut.zen.game.elements.tiles.VillageTile;
import fr.iut.zen.game.elements.tiles.WheatFieldsTile;

public class WheatFields implements Card {
	private final String wheatFieldsPATH = "pictures/WheatFieldsCard.png";
	
	
	@Override
	public String getImagePath() {
		
		return wheatFieldsPATH;
	}

	@Override
	public String getType() {

		return "Road";
	}


	@Override
	public Tile getTile(GridPosition p) {
		Objects.requireNonNull(p);
		return new WheatFieldsTile(p);
	}
	
	@Override
	
	public String toString() {
		return "WheatFields Card";
	}


}