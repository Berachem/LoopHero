package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.ScorchWorm;
import fr.iut.zen.game.elements.enemies.Spider;

public class RuinsTile implements Tile,Serializable {
	private final String ruinsTilePATH = "pictures/RuinsTile.png";
	private final GridPosition position;
	
	public RuinsTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	public static void spawnScorchWorm(ArrayList<Tile> placedTiles, List<Mobs> MobsOnthePath, int LoopCount) {
		for (Tile t : placedTiles) {
			if ( t instanceof RuinsTile) {

				MobsOnthePath.add(new ScorchWorm(t.getPosition(),LoopCount));
			}
		}
		
	}
	
	
	@Override
	public String getImagePath() {
		
		return ruinsTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return new ScorchWorm(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruinsTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuinsTile other = (RuinsTile) obj;
		return Objects.equals(ruinsTilePATH, other.ruinsTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		return position;
	}


	@Override
	public String toString() {
		return "RuinsTile [ruinsTilePATH=" + ruinsTilePATH + ", position=" + position + "]";
	}

	@Override
	public String getResource() {
		ArrayList<String> listResources = new ArrayList<String>(Arrays.asList("Stable Branches", "Preserved Pebble", "Scrap Metal", "Ration"));		
		Random rand = new Random();
		return listResources.get(rand.nextInt(listResources.size()));
	}
}
