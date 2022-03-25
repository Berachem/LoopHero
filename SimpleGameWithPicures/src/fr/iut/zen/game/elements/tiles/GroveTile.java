package fr.iut.zen.game.elements.tiles;

import java.util.List;
import java.util.Objects;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.elements.Hero;
import fr.iut.zen.game.elements.enemies.Mobs;
import fr.iut.zen.game.elements.enemies.Ratwolf;

public class GroveTile  implements Tile {
	
	final String groveTilePATH = "pictures/Grove_tile.png";
	final GridPosition position;
	
	public GroveTile(GridPosition p) {
		position = p;
	}
	
	
	@Override
	public String getImagePath() {
		
		return groveTilePATH;
	}

	@Override
	public Mobs spawn(List<GridPosition> path) {
		
		return new Ratwolf(path.get((int)(Math.random() *path.size())), 0);
	}

	@Override
	public void effectOnHero(Hero hero) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(groveTilePATH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroveTile other = (GroveTile) obj;
		return Objects.equals(groveTilePATH, other.groveTilePATH);
	}


	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	

}
