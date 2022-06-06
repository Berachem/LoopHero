package fr.iut.zen.game.elements.tiles;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;

public class VillageTile implements Tile,Serializable {
	
	private final String villageTilePATH = "pictures/VillageTile.png";
	private final GridPosition position;
	
	public VillageTile(GridPosition p) {
		Objects.requireNonNull(p);
		position = p;
	}
	
	/**
	 * Applies the effect of the Village Tiles
	 * - Starts a quest : the hero has to kill a random mob that has it's stats upped
	 * - heal of 15+5*LoopCount
	 */
	public static Mobs questStartVillage( List<Mobs> mobsOnthePath, Mobs QuestMobTarget, int LoopCount, Hero hero) {
			
		Mobs Target = null;
		
			if (!mobsOnthePath.isEmpty()) {
				int random = new Random().nextInt(mobsOnthePath.size());
				Target = mobsOnthePath.get(random);
				mobsOnthePath.get(random).getStats().addDamage(4*LoopCount);
				mobsOnthePath.get(random).healValue(4*LoopCount);
				System.out.println("The Mob target is "+Target);
				
			}

			hero.healValue(15+5*LoopCount);
			return Target;
		
			
		
	}
	
	
	@Override
	public String getImagePath() {
		
		return villageTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		Objects.requireNonNull(path);
		return null;
	}

	@Override
	public void effectOnHero(Hero hero) {
		Objects.requireNonNull(hero);
		
	}
	

	@Override
	public String toString() {
		return "VillageTile [villageTilePATH=" + villageTilePATH + ", position=" + position + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(villageTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VillageTile other = (VillageTile) obj;
		return Objects.equals(villageTilePATH, other.villageTilePATH);
	}
	
	@Override
	public GridPosition getPosition() {
		return position;
	}

	@Override
	public String getResource() {
		return null;
	}
	
	
}

