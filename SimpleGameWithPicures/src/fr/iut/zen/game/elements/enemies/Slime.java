package fr.iut.zen.game.elements.enemies;

import fr.iut.zen.game.GridPosition;

public class Slime implements Mobs {
	
	final String slimePATH = "pictures/green-slime.png";
	private int health;
	private final int damage;
	private final GridPosition locationSlime;
	// le reste jte laisse gérer stp ^^
	
	public Slime(GridPosition locationSlime, int LoopCount) {
		health=13;
		damage=3;
		this.locationSlime = locationSlime;
	}
	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return damage;
	}
	@Override
	public void attacked(int dmg) {
		// TODO Auto-generated method stub
		health-=dmg;
		
	}
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return health>0;
	}
	@Override
	public boolean isInPosition(GridPosition p) {
		// TODO Auto-generated method stub
		return locationSlime.equals(p);
	}
	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return slimePATH;
	}
	@Override
	public GridPosition getPosition() {
		// TODO Auto-generated method stub
		return locationSlime;
	}
	@Override
	public int dropRessources() {
		// TODO Auto-generated method stub
		return 30;
	}


}
